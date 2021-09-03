package com.ltyocg.commander

import com.ltyocg.commander.employeehandle.EmployeeHandle
import com.ltyocg.commander.messagingservice.MessagingService
import com.ltyocg.commander.paymentservice.PaymentService
import com.ltyocg.commander.queue.QueueDatabase
import com.ltyocg.commander.queue.QueueTask
import com.ltyocg.commander.shippingservice.ShippingService
import org.slf4j.LoggerFactory
import kotlin.concurrent.thread

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
    private val log = LoggerFactory.getLogger(this::class.java)
    private var queueItems = 0
    private var finalSiteMsgShown: Boolean = false

    fun placeOrder(order: Order) {
        Retry(
            {
                if (it.isNotEmpty()) {
                    if (it[0] is DatabaseUnavailableException) log.debug("Order {}: Error in connecting to shipping service, trying again..", order.id)
                    else log.debug("Order {}: Error in creating shipping request..", order.id)
                    throw it.removeAt(0)
                }
                log.info("Order {}: Shipping placed successfully, transaction id: {}", order.id, shippingService.receiveRequest(order.item, order.user.address))
                log.info("Order has been placed and will be shipped to you. Please wait while we make your payment... ")
                sendPaymentRequest(order)
            },
            { obj: Order, err ->
                when (err) {
                    is ShippingNotPossibleException -> {
                        log.info("Shipping is currently not possible to your address. We are working on the problem and will get back to you asap.")
                        finalSiteMsgShown = true
                        log.info("Order {}: Shipping not possible to address, trying to add problem to employee db..", order.id)
                        employeeHandleIssue(obj)
                    }
                    is ItemUnavailableException -> {
                        log.info("This item is currently unavailable. We will inform you as soon as the item becomes available again.")
                        finalSiteMsgShown = true
                        log.info("Order {}: Item {} unavailable, trying to add " + "problem to employee handle..", order.id, order.item)
                        employeeHandleIssue(obj)
                    }
                    else -> {
                        log.info("Sorry, there was a problem in creating your order. Please try later.")
                        log.error("Order {}: Shipping service unavailable, order not placed..", order.id)
                        finalSiteMsgShown = true
                    }
                }
            },
            numOfRetries,
            retryDuration,
            { it is DatabaseUnavailableException }
        ).perform(shippingService.exceptionsList, order)
    }

    private fun sendPaymentRequest(order: Order) {
        if (System.currentTimeMillis() - order.createdTime >= paymentTime) {
            if (order.paid == Order.PaymentStatus.TRYING) {
                order.paid = Order.PaymentStatus.NOT_DONE
                sendPaymentFailureMessage(order)
                log.error("Order {}: Payment time for order over, failed and returning..", order.id)
            }
            return
        }
        thread {
            try {
                Retry(
                    {
                        if (it.isNotEmpty()) {
                            if (it[0] is DatabaseUnavailableException) log.debug("Order {}: Error in connecting to payment service, trying again..", order.id)
                            else log.debug("Order {}: Error in creating payment request..", order.id)
                            throw it.removeAt(0)
                        }
                        if (order.paid == Order.PaymentStatus.TRYING) {
                            val transactionId = paymentService.receiveRequest(order.price)
                            order.paid = Order.PaymentStatus.DONE
                            log.info("Order {}: Payment successful, transaction Id: {}", order.id, transactionId)
                            if (!finalSiteMsgShown) {
                                log.info("Payment made successfully, thank you for shopping with us!!")
                                finalSiteMsgShown = true
                            }
                            sendSuccessMessage(order)
                        }
                    },
                    { o: Order, err ->
                        if (err is PaymentDetailsErrorException) {
                            if (!finalSiteMsgShown) {
                                log.info("There was an error in payment. Your account/card details may have been incorrect. Meanwhile, your order has been converted to COD and will be shipped.")
                                finalSiteMsgShown = true
                            }
                            log.error("Order {}: Payment details incorrect, failed..", order.id)
                            o.paid = Order.PaymentStatus.NOT_DONE
                            sendPaymentFailureMessage(o)
                        } else {
                            if (o.messageSent == Order.MessageSent.NONE_SENT) {
                                if (!finalSiteMsgShown) {
                                    log.info("There was an error in payment. We are on it, and will get back to you asap. Don't worry, your order has been placed and will be shipped.")
                                    finalSiteMsgShown = true
                                }
                                log.warn("Order {}: Payment error, going to queue..", order.id)
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
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun updateQueue(qt: QueueTask) {
        if (System.currentTimeMillis() - qt.order.createdTime >= queueTime) {
            log.trace("Order {}: Queue time for order over, failed..", qt.order.id)
            return
        } else if (
            qt.taskType == QueueTask.TaskType.PAYMENT && qt.order.paid != Order.PaymentStatus.TRYING ||
            qt.taskType == QueueTask.TaskType.MESSAGING && (
                    qt.messageType == 1 && qt.order.messageSent != Order.MessageSent.NONE_SENT ||
                            qt.order.messageSent == Order.MessageSent.PAYMENT_FAIL ||
                            qt.order.messageSent == Order.MessageSent.PAYMENT_SUCCESSFUL
                    ) ||
            qt.taskType == QueueTask.TaskType.EMPLOYEE_DB && qt.order.addedToEmployeeHandle
        ) {
            log.trace("Order {}: Not queueing task since task already done..", qt.order.id)
            return
        }
        thread {
            try {
                Retry(
                    {
                        if (it.isNotEmpty()) {
                            log.warn("Order {}: Error in connecting to queue db, trying again..", qt.order.id)
                            throw it.removeAt(0)
                        }
                        queue.add(qt)
                        queueItems++
                        log.info("Order {}: {} task enqueued..", qt.order.id, qt.type)
                        tryDoingTasksInQueue()
                    },
                    { qt1: QueueTask, _ ->
                        if (qt1.taskType == QueueTask.TaskType.PAYMENT) {
                            qt1.order.paid = Order.PaymentStatus.NOT_DONE
                            sendPaymentFailureMessage(qt1.order)
                            log.error("Order {}: Unable to enqueue payment task, payment failed..", qt1.order.id)
                        }
                        log.error("Order {}: Unable to enqueue task of type {}, trying to add to employee handle..", qt1.order.id, qt1.type)
                        employeeHandleIssue(qt1.order)
                    },
                    numOfRetries,
                    retryDuration,
                    { it is DatabaseUnavailableException }
                ).perform(queue.exceptionsList, qt)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun tryDoingTasksInQueue() {
        thread {
            try {
                Retry(
                    {
                        if (it.isNotEmpty()) {
                            log.warn("Error in accessing queue db to do tasks, trying again..")
                            throw it.removeAt(0)
                        }
                        if (queueItems != 0) {
                            val qt = queue.peek()
                            log.trace("Order {}: Started doing task of type {}", qt.order.id, qt.type)
                            if (qt.firstAttemptTime == -1L) qt.firstAttemptTime = System.currentTimeMillis()
                            if (System.currentTimeMillis() - qt.firstAttemptTime >= queueTaskTime) {
                                tryDequeue()
                                log.trace("Order {}: This queue task of type {} does not need to be done anymore (timeout), dequeue..", qt.order.id, qt.type)
                            } else when (qt.taskType) {
                                QueueTask.TaskType.PAYMENT -> if (qt.order.paid != Order.PaymentStatus.TRYING) {
                                    tryDequeue()
                                    log.trace("Order {}: This payment task already done, dequeueing..", qt.order.id)
                                } else {
                                    sendPaymentRequest(qt.order)
                                    log.debug("Order {}: Trying to connect to payment service..", qt.order.id)
                                }
                                QueueTask.TaskType.MESSAGING -> if (qt.order.messageSent == Order.MessageSent.PAYMENT_FAIL || qt.order.messageSent == Order.MessageSent.PAYMENT_SUCCESSFUL) {
                                    tryDequeue()
                                    log.trace("Order {}: This messaging task does not need to be done, dequeue..", qt.order.id)
                                } else if (qt.messageType == 1 && (qt.order.messageSent != Order.MessageSent.NONE_SENT || qt.order.paid != Order.PaymentStatus.TRYING)) {
                                    tryDequeue()
                                    log.trace("Order {}: This messaging task does not need to be done, dequeue..", qt.order.id)
                                } else when (qt.messageType) {
                                    0 -> {
                                        sendPaymentFailureMessage(qt.order)
                                        log.debug("Order {}: Trying to connect to messaging service..", qt.order.id)
                                    }
                                    1 -> {
                                        sendPaymentPossibleErrorMsg(qt.order)
                                        log.debug("Order {}: Trying to connect to messaging service..", qt.order.id)
                                    }
                                    2 -> {
                                        sendSuccessMessage(qt.order)
                                        log.debug("Order {}: Trying to connect to messaging service..", qt.order.id)
                                    }
                                }
                                QueueTask.TaskType.EMPLOYEE_DB -> if (qt.order.addedToEmployeeHandle) {
                                    tryDequeue()
                                    log.trace("Order {}: This employee handle task already done, dequeue..", qt.order.id)
                                } else {
                                    employeeHandleIssue(qt.order)
                                    log.debug("Order {}: Trying to connect to employee handle..", qt.order.id)
                                }
                            }
                        }
                        if (queueItems == 0) log.trace("Queue is empty, returning..")
                        else {
                            Thread.sleep(queueTaskTime / 3)
                            tryDoingTasksInQueue()
                        }
                    },
                    { _: QueueTask?, _ -> },
                    numOfRetries,
                    retryDuration,
                    { it is DatabaseUnavailableException }
                ).perform(queue.exceptionsList, null)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun tryDequeue() {
        thread {
            try {
                Retry(
                    {
                        if (it.isNotEmpty()) {
                            log.warn("Error in accessing queue db to dequeue task, trying again..")
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
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun sendSuccessMessage(order: Order) {
        if (order.isMessageTimeOver) {
            log.trace("Order {}: Message time for order over, returning..", order.id)
            return
        }
        thread {
            try {
                Retry(
                    {
                        if (it.isNotEmpty()) {
                            if (it[0] is DatabaseUnavailableException) log.debug("Order {}: Error in connecting to messaging service (Payment Success msg), trying again..", order.id)
                            else log.debug("Order {}: Error in creating Payment Success messaging request..", order.id)
                            throw it.removeAt(0)
                        }
                        if (order.messageSent != Order.MessageSent.PAYMENT_FAIL && order.messageSent != Order.MessageSent.PAYMENT_SUCCESSFUL) {
                            order.messageSent = Order.MessageSent.PAYMENT_SUCCESSFUL
                            log.info("Order {}: Payment Success message sent, request Id: {}", order.id, messagingService.receiveRequest(2))
                        }
                    },
                    { o: Order, _ ->
                        if ((o.messageSent == Order.MessageSent.NONE_SENT || o.messageSent == Order.MessageSent.PAYMENT_TRYING) && !o.isMessageTimeOver) {
                            updateQueue(QueueTask(order, QueueTask.TaskType.MESSAGING, 2))
                            log.info("Order {}: Error in sending Payment Success message, trying to queue task and add to employee handle..", order.id)
                            employeeHandleIssue(o)
                        }
                    },
                    numOfRetries,
                    retryDuration,
                    { it is DatabaseUnavailableException }
                ).perform(messagingService.exceptionsList, order)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun sendPaymentFailureMessage(order: Order) {
        if (order.isMessageTimeOver) {
            log.trace("Order {}: Message time for order over, returning..", order.id)
            return
        }
        thread {
            try {
                Retry(
                    {
                        if (it.isNotEmpty()) {
                            if (it[0] is DatabaseUnavailableException) log.debug("Order {}: Error in connecting to messaging service (Payment Failure msg), trying again..", order.id)
                            else log.debug("Order {}: Error in creating Payment Failure message request..", order.id)
                            throw it.removeAt(0)
                        }
                        if (order.messageSent != Order.MessageSent.PAYMENT_FAIL && order.messageSent != Order.MessageSent.PAYMENT_SUCCESSFUL) {
                            order.messageSent = Order.MessageSent.PAYMENT_FAIL
                            log.info("Order {}: Payment Failure message sent, request Id: {}", order.id, messagingService.receiveRequest(0))
                        }
                    },
                    { o: Order, _ ->
                        if ((o.messageSent == Order.MessageSent.NONE_SENT || o.messageSent == Order.MessageSent.PAYMENT_TRYING) && !o.isMessageTimeOver) {
                            updateQueue(QueueTask(order, QueueTask.TaskType.MESSAGING, 0))
                            log.info("Order {}: Error in sending Payment Failure message, trying to queue task and add to employee handle..", order.id)
                            employeeHandleIssue(o)
                        }
                    },
                    numOfRetries,
                    retryDuration,
                    { it is DatabaseUnavailableException }
                ).perform(messagingService.exceptionsList, order)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun sendPaymentPossibleErrorMsg(order: Order) {
        if (order.isMessageTimeOver) {
            log.trace("Order {}: Message time for order over, returning..", order.id)
            return
        }
        thread {
            try {
                Retry(
                    {
                        if (it.isNotEmpty()) {
                            if (it[0] is DatabaseUnavailableException) log.debug("Order {}: Error in connecting to messaging service (Payment Error msg), trying again..", order.id)
                            else log.debug("Order {}: Error in creating Payment Error message request..", order.id)
                            throw it.removeAt(0)
                        }
                        if (order.paid == Order.PaymentStatus.TRYING && order.messageSent == Order.MessageSent.NONE_SENT) {
                            order.messageSent = Order.MessageSent.PAYMENT_TRYING
                            log.info("Order {}: Payment Error message sent, request Id: {}", order.id, messagingService.receiveRequest(1))
                        }
                    },
                    { o: Order, _ ->
                        if (o.messageSent == Order.MessageSent.NONE_SENT && o.paid == Order.PaymentStatus.TRYING && !o.isMessageTimeOver) {
                            updateQueue(QueueTask(order, QueueTask.TaskType.MESSAGING, 1))
                            log.info("Order {}: Error in sending Payment Error message, trying to queue task and add to employee handle..", order.id)
                            employeeHandleIssue(o)
                        }
                    },
                    numOfRetries,
                    retryDuration,
                    { it is DatabaseUnavailableException }
                ).perform(messagingService.exceptionsList, order)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun employeeHandleIssue(order: Order) {
        if (System.currentTimeMillis() - order.createdTime >= employeeTime) {
            log.trace("Order {}: Employee handle time for order over, returning..", order.id)
            return
        }
        thread {
            try {
                Retry(
                    {
                        if (it.isNotEmpty()) {
                            log.warn("Order {}: Error in connecting to employee handle, trying again..", order.id)
                            throw it.removeAt(0)
                        }
                        if (!order.addedToEmployeeHandle) {
                            employeeDb.receiveRequest(order)
                            order.addedToEmployeeHandle = true
                            log.info("Order {}: Added order to employee database", order.id)
                        }
                    },
                    { o: Order, _ ->
                        if (!o.addedToEmployeeHandle && System.currentTimeMillis() - order.createdTime < employeeTime) {
                            updateQueue(QueueTask(order, QueueTask.TaskType.EMPLOYEE_DB, -1))
                            log.warn("Order {}: Error in adding to employee db, trying to queue task..", order.id)
                        }
                    },
                    numOfRetries,
                    retryDuration,
                    { it is DatabaseUnavailableException }
                ).perform(employeeDb.exceptionsList, order)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private val Order.isMessageTimeOver: Boolean
        get() = System.currentTimeMillis() - createdTime >= messageTime
}