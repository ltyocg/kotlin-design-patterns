import employeehandle.EmployeeHandle
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import messagingservice.MessagingService
import paymentservice.PaymentService
import queue.QueueDatabase
import queue.QueueTask
import shippingservice.ShippingService

class Commander(
    private val employeeDb: EmployeeHandle,
    private val paymentService: PaymentService,
    private val shippingService: ShippingService,
    private val messagingService: MessagingService,
    private val queue: QueueDatabase,
    private val numOfRetries: Int,
    private val retryDuration: Long,
    private val queueTime: Long,
    private val queueTaskTime: Long,
    private val paymentTime: Long,
    private val messageTime: Long,
    private val employeeTime: Long,
) {
    private val logger = KotlinLogging.logger {}
    private var queueItems = 0
    private var finalSiteMsgShown: Boolean = false

    suspend fun placeOrder(order: Order) {
        Retry(
            {
                if (it.isNotEmpty()) {
                    logger.debug {
                        if (it[0] is DatabaseUnavailableException) "Order ${order.id}: Error in connecting to shipping service, trying again.."
                        else "Order ${order.id}: Error in creating shipping request.."
                    }
                    throw it.removeAt(0)
                }
                logger.info { "Order ${order.id}: Shipping placed successfully, transaction id: ${shippingService.receiveRequest(order.item, order.user.address)}" }
                logger.info { "Order has been placed and will be shipped to you. Please wait while we make your payment... " }
                sendPaymentRequest(order)
            },
            { obj: Order, err ->
                when (err) {
                    is ShippingNotPossibleException -> {
                        logger.info { "Shipping is currently not possible to your address. We are working on the problem and will get back to you asap." }
                        finalSiteMsgShown = true
                        logger.info { "Order ${order.id}: Shipping not possible to address, trying to add problem to employee db.." }
                        employeeHandleIssue(obj)
                    }

                    is ItemUnavailableException -> {
                        logger.info { "This item is currently unavailable. We will inform you as soon as the item becomes available again." }
                        finalSiteMsgShown = true
                        logger.info { "Order ${order.id}: Item ${order.item} unavailable, trying to add " + "problem to employee handle.." }
                        employeeHandleIssue(obj)
                    }

                    else -> {
                        logger.info { "Sorry, there was a problem in creating your order. Please try later." }
                        logger.error { "Order ${order.id}: Shipping service unavailable, order not placed.." }
                        finalSiteMsgShown = true
                    }
                }
            },
            numOfRetries,
            retryDuration,
            { it is DatabaseUnavailableException }
        ).perform(shippingService.exceptionsList, order)
    }

    private suspend fun sendPaymentRequest(order: Order) = coroutineScope {
        if (System.currentTimeMillis() - order.createdTime >= paymentTime) {
            if (order.paid == Order.PaymentStatus.TRYING) {
                order.paid = Order.PaymentStatus.NOT_DONE
                sendPaymentFailureMessage(order)
                logger.error { "Order ${order.id}: Payment time for order over, failed and returning.." }
            }
            return@coroutineScope
        }
        launch {
            Retry(
                {
                    if (it.isNotEmpty()) {
                        logger.debug {
                            if (it[0] is DatabaseUnavailableException) "Order ${order.id}: Error in connecting to payment service, trying again.."
                            else "Order ${order.id}: Error in creating payment request.."
                        }
                        throw it.removeAt(0)
                    }
                    if (order.paid == Order.PaymentStatus.TRYING) {
                        val transactionId = paymentService.receiveRequest(order.price)
                        order.paid = Order.PaymentStatus.DONE
                        logger.info { "Order ${order.id}: Payment successful, transaction Id: $transactionId" }
                        if (!finalSiteMsgShown) {
                            logger.info { "Payment made successfully, thank you for shopping with us!!" }
                            finalSiteMsgShown = true
                        }
                        sendSuccessMessage(order)
                    }
                },
                { o: Order, err ->
                    if (err is PaymentDetailsErrorException) {
                        if (!finalSiteMsgShown) {
                            logger.info { "There was an error in payment. Your account/card details may have been incorrect. Meanwhile, your order has been converted to COD and will be shipped." }
                            finalSiteMsgShown = true
                        }
                        logger.error { "Order ${order.id}: Payment details incorrect, failed.." }
                        o.paid = Order.PaymentStatus.NOT_DONE
                        sendPaymentFailureMessage(o)
                    } else {
                        if (o.messageSent == Order.MessageSent.NONE_SENT) {
                            if (!finalSiteMsgShown) {
                                logger.info { "There was an error in payment. We are on it, and will get back to you asap. Don't worry, your order has been placed and will be shipped." }
                                finalSiteMsgShown = true
                            }
                            logger.warn { "Order ${order.id}: Payment error, going to queue.." }
                            sendPaymentPossibleErrorMsg(o)
                        }
                        if (o.paid == Order.PaymentStatus.TRYING && System.currentTimeMillis() - o.createdTime < paymentTime)
                            updateQueue(QueueTask(order, QueueTask.TaskType.PAYMENT, -1))
                    }
                },
                numOfRetries,
                retryDuration,
                { it is DatabaseUnavailableException }
            ).perform(paymentService.exceptionsList, order)
        }
    }

    private suspend fun updateQueue(qt: QueueTask): Unit = coroutineScope {
        if (System.currentTimeMillis() - qt.order.createdTime >= queueTime) {
            logger.trace { "Order ${qt.order.id}: Queue time for order over, failed.." }
            return@coroutineScope
        } else if (
            qt.taskType == QueueTask.TaskType.PAYMENT && qt.order.paid != Order.PaymentStatus.TRYING ||
            qt.taskType == QueueTask.TaskType.MESSAGING && (
                    qt.messageType == 1 && qt.order.messageSent != Order.MessageSent.NONE_SENT ||
                            qt.order.messageSent == Order.MessageSent.PAYMENT_FAIL ||
                            qt.order.messageSent == Order.MessageSent.PAYMENT_SUCCESSFUL
                    ) ||
            qt.taskType == QueueTask.TaskType.EMPLOYEE_DB && qt.order.addedToEmployeeHandle
        ) {
            logger.trace { "Order ${qt.order.id}: Not queueing task since task already done.." }
            return@coroutineScope
        }
        launch {
            Retry(
                {
                    if (it.isNotEmpty()) {
                        logger.warn { "Order ${qt.order.id}: Error in connecting to queue db, trying again.." }
                        throw it.removeAt(0)
                    }
                    queue.add(qt)
                    queueItems++
                    logger.info { "Order ${qt.order.id}: ${qt.type} task enqueued.." }
                    tryDoingTasksInQueue()
                },
                { qt1: QueueTask, _ ->
                    if (qt1.taskType == QueueTask.TaskType.PAYMENT) {
                        qt1.order.paid = Order.PaymentStatus.NOT_DONE
                        sendPaymentFailureMessage(qt1.order)
                        logger.error { "Order ${qt1.order.id}: Unable to enqueue payment task, payment failed.." }
                    }
                    logger.error { "Order ${qt1.order.id}: Unable to enqueue task of type ${qt1.type}, trying to add to employee handle.." }
                    employeeHandleIssue(qt1.order)
                },
                numOfRetries,
                retryDuration,
                { it is DatabaseUnavailableException }
            ).perform(queue.exceptionsList, qt)
        }
    }

    private suspend fun tryDoingTasksInQueue(): Unit = coroutineScope {
        launch {
            Retry(
                {
                    if (it.isNotEmpty()) {
                        logger.warn { "Error in accessing queue db to do tasks, trying again.." }
                        throw it.removeAt(0)
                    }
                    if (queueItems != 0) {
                        val qt = queue.peek()
                        logger.trace { "Order ${qt.order.id}: Started doing task of type ${qt.type}" }
                        if (qt.firstAttemptTime == -1L) qt.firstAttemptTime = System.currentTimeMillis()
                        if (System.currentTimeMillis() - qt.firstAttemptTime >= queueTaskTime) {
                            tryDequeue()
                            logger.trace { "Order ${qt.order.id}: This queue task of type ${qt.type} does not need to be done anymore (timeout), dequeue.." }
                        } else when (qt.taskType) {
                            QueueTask.TaskType.PAYMENT -> if (qt.order.paid != Order.PaymentStatus.TRYING) {
                                tryDequeue()
                                logger.trace { "Order ${qt.order.id}: This payment task already done, dequeueing.." }
                            } else {
                                sendPaymentRequest(qt.order)
                                logger.debug { "Order ${qt.order.id}: Trying to connect to payment service.." }
                            }

                            QueueTask.TaskType.MESSAGING -> if (qt.order.messageSent == Order.MessageSent.PAYMENT_FAIL || qt.order.messageSent == Order.MessageSent.PAYMENT_SUCCESSFUL) {
                                tryDequeue()
                                logger.trace { "Order ${qt.order.id}: This messaging task does not need to be done, dequeue.." }
                            } else if (qt.messageType == 1 && (qt.order.messageSent != Order.MessageSent.NONE_SENT || qt.order.paid != Order.PaymentStatus.TRYING)) {
                                tryDequeue()
                                logger.trace { "Order ${qt.order.id}: This messaging task does not need to be done, dequeue.." }
                            } else when (qt.messageType) {
                                0 -> {
                                    sendPaymentFailureMessage(qt.order)
                                    logger.debug { "Order ${qt.order.id}: Trying to connect to messaging service.." }
                                }

                                1 -> {
                                    sendPaymentPossibleErrorMsg(qt.order)
                                    logger.debug { "Order ${qt.order.id}: Trying to connect to messaging service.." }
                                }

                                2 -> {
                                    sendSuccessMessage(qt.order)
                                    logger.debug { "Order ${qt.order.id}: Trying to connect to messaging service.." }
                                }
                            }

                            QueueTask.TaskType.EMPLOYEE_DB -> if (qt.order.addedToEmployeeHandle) {
                                tryDequeue()
                                logger.trace { "Order ${qt.order.id}: This employee handle task already done, dequeue.." }
                            } else {
                                employeeHandleIssue(qt.order)
                                logger.debug { "Order ${qt.order.id}: Trying to connect to employee handle.." }
                            }
                        }
                    }
                    if (queueItems == 0) logger.trace { "Queue is empty, returning.." }
                    else {
                        delay(queueTaskTime / 3)
                        tryDoingTasksInQueue()
                    }
                },
                { _: QueueTask?, _ -> },
                numOfRetries,
                retryDuration,
                { it is DatabaseUnavailableException }
            ).perform(queue.exceptionsList, null)
        }
    }

    private suspend fun tryDequeue() = coroutineScope {
        launch {
            Retry(
                {
                    if (it.isNotEmpty()) {
                        logger.warn { "Error in accessing queue db to dequeue task, trying again.." }
                        throw it.removeAt(0)
                    }
                    queue.dequeue()
                    queueItems--
                },
                { _: QueueTask?, _ -> },
                numOfRetries,
                retryDuration,
                { it is DatabaseUnavailableException }
            ).perform(queue.exceptionsList, null)
        }
    }

    private suspend fun sendSuccessMessage(order: Order) = coroutineScope {
        if (order.isMessageTimeOver) {
            logger.trace { "Order ${order.id}: Message time for order over, returning.." }
            return@coroutineScope
        }
        launch {
            Retry(
                {
                    if (it.isNotEmpty()) {
                        if (it[0] is DatabaseUnavailableException) logger.debug { "Order ${order.id}: Error in connecting to messaging service (Payment Success msg), trying again.." }
                        else logger.debug { "Order ${order.id}: Error in creating Payment Success messaging request.." }
                        throw it.removeAt(0)
                    }
                    if (order.messageSent != Order.MessageSent.PAYMENT_FAIL && order.messageSent != Order.MessageSent.PAYMENT_SUCCESSFUL) {
                        order.messageSent = Order.MessageSent.PAYMENT_SUCCESSFUL
                        logger.info { "Order ${order.id}: Payment Success message sent, request Id: ${messagingService.receiveRequest(2)}" }
                    }
                },
                { o: Order, _ ->
                    if ((o.messageSent == Order.MessageSent.NONE_SENT || o.messageSent == Order.MessageSent.PAYMENT_TRYING) && !o.isMessageTimeOver) {
                        updateQueue(QueueTask(order, QueueTask.TaskType.MESSAGING, 2))
                        logger.info { "Order ${order.id}: Error in sending Payment Success message, trying to queue task and add to employee handle.." }
                        employeeHandleIssue(o)
                    }
                },
                numOfRetries,
                retryDuration,
                { it is DatabaseUnavailableException }
            ).perform(messagingService.exceptionsList, order)
        }
    }

    private suspend fun sendPaymentFailureMessage(order: Order) = coroutineScope {
        if (order.isMessageTimeOver) {
            logger.trace { "Order ${order.id}: Message time for order over, returning.." }
            return@coroutineScope
        }
        launch {
            Retry(
                {
                    if (it.isNotEmpty()) {
                        if (it[0] is DatabaseUnavailableException) logger.debug { "Order ${order.id}: Error in connecting to messaging service (Payment Failure msg), trying again.." }
                        else logger.debug { "Order ${order.id}: Error in creating Payment Failure message request.." }
                        throw it.removeAt(0)
                    }
                    if (order.messageSent != Order.MessageSent.PAYMENT_FAIL && order.messageSent != Order.MessageSent.PAYMENT_SUCCESSFUL) {
                        order.messageSent = Order.MessageSent.PAYMENT_FAIL
                        logger.info { "Order ${order.id}: Payment Failure message sent, request Id: ${messagingService.receiveRequest(0)}" }
                    }
                },
                { o: Order, _ ->
                    if ((o.messageSent == Order.MessageSent.NONE_SENT || o.messageSent == Order.MessageSent.PAYMENT_TRYING) && !o.isMessageTimeOver) {
                        updateQueue(QueueTask(order, QueueTask.TaskType.MESSAGING, 0))
                        logger.info { "Order ${order.id}: Error in sending Payment Failure message, trying to queue task and add to employee handle.." }
                        employeeHandleIssue(o)
                    }
                },
                numOfRetries,
                retryDuration,
                { it is DatabaseUnavailableException }
            ).perform(messagingService.exceptionsList, order)
        }
    }

    private suspend fun sendPaymentPossibleErrorMsg(order: Order) = coroutineScope {
        if (order.isMessageTimeOver) {
            logger.trace { "Order ${order.id}: Message time for order over, returning.." }
            return@coroutineScope
        }
        launch {
            Retry(
                {
                    if (it.isNotEmpty()) {
                        logger.debug {
                            if (it[0] is DatabaseUnavailableException) "Order ${order.id}: Error in connecting to messaging service (Payment Error msg), trying again.."
                            else "Order ${order.id}: Error in creating Payment Error message request.."
                        }
                        throw it.removeAt(0)
                    }
                    if (order.paid == Order.PaymentStatus.TRYING && order.messageSent == Order.MessageSent.NONE_SENT) {
                        order.messageSent = Order.MessageSent.PAYMENT_TRYING
                        logger.info { "Order ${order.id}: Payment Error message sent, request Id: ${messagingService.receiveRequest(1)}" }
                    }
                },
                { o: Order, _ ->
                    if (o.messageSent == Order.MessageSent.NONE_SENT && o.paid == Order.PaymentStatus.TRYING && !o.isMessageTimeOver) {
                        updateQueue(QueueTask(order, QueueTask.TaskType.MESSAGING, 1))
                        logger.info { "Order ${order.id}: Error in sending Payment Error message, trying to queue task and add to employee handle.." }
                        employeeHandleIssue(o)
                    }
                },
                numOfRetries,
                retryDuration,
                { it is DatabaseUnavailableException }
            ).perform(messagingService.exceptionsList, order)
        }
    }

    private suspend fun employeeHandleIssue(order: Order) = coroutineScope {
        if (System.currentTimeMillis() - order.createdTime >= employeeTime) {
            logger.trace { "Order ${order.id}: Employee handle time for order over, returning.." }
            return@coroutineScope
        }
        launch {
            Retry(
                {
                    if (it.isNotEmpty()) {
                        logger.warn { "Order ${order.id}: Error in connecting to employee handle, trying again.." }
                        throw it.removeAt(0)
                    }
                    if (!order.addedToEmployeeHandle) {
                        employeeDb.receiveRequest(order)
                        order.addedToEmployeeHandle = true
                        logger.info { "Order ${order.id}: Added order to employee database" }
                    }
                },
                { o: Order, _ ->
                    if (!o.addedToEmployeeHandle && System.currentTimeMillis() - order.createdTime < employeeTime) {
                        updateQueue(QueueTask(order, QueueTask.TaskType.EMPLOYEE_DB, -1))
                        logger.warn { "Order ${order.id}: Error in adding to employee db, trying to queue task.." }
                    }
                },
                numOfRetries,
                retryDuration,
                { it is DatabaseUnavailableException }
            ).perform(employeeDb.exceptionsList, order)
        }
    }

    private val Order.isMessageTimeOver: Boolean
        get() = System.currentTimeMillis() - createdTime >= messageTime
}
