import employeehandle.EmployeeDatabase
import employeehandle.EmployeeHandle
import messagingservice.MessagingDatabase
import messagingservice.MessagingService
import paymentservice.PaymentDatabase
import paymentservice.PaymentService
import queue.QueueDatabase
import shippingservice.ShippingDatabase
import shippingservice.ShippingService

private const val NUM_OF_RETRIES = 3
private const val RETRY_DURATION = 30000L
private const val QUEUE_TIME = 240000L
private const val QUEUE_TASK_TIME = 60000L
private const val PAYMENT_TIME = 120000L
private const val MESSAGE_TIME: Long = 150000L
private const val EMPLOYEE_TIME: Long = 240000L

suspend fun main() {
    Commander(
        EmployeeHandle(EmployeeDatabase()),
        PaymentService(PaymentDatabase(), *Array(6) { DatabaseUnavailableException() }),
        ShippingService(ShippingDatabase()),
        MessagingService(MessagingDatabase(), *Array(2) { DatabaseUnavailableException() }),
        QueueDatabase(*Array(2) { DatabaseUnavailableException() }),
        NUM_OF_RETRIES,
        RETRY_DURATION,
        QUEUE_TIME,
        QUEUE_TASK_TIME,
        PAYMENT_TIME,
        MESSAGE_TIME,
        EMPLOYEE_TIME
    ).placeOrder(Order(User("Jim", "ABCD"), "book", 10f))
}