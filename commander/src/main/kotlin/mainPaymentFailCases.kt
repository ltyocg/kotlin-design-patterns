import employeehandle.EmployeeHandle
import messagingservice.MessagingService
import paymentservice.PaymentService
import queue.QueueDatabase
import shippingservice.ShippingService

suspend fun main() = Commander(
    EmployeeHandle(),
    PaymentService(*Array(2) { DatabaseUnavailableException() }),
    ShippingService(),
    MessagingService(DatabaseUnavailableException()),
    QueueDatabase(DatabaseUnavailableException()),
    3,
    30000L,
    240000L,
    60000L,
    120000L,
    150000L,
    240000L
).placeOrder(Order(User("Jim", "ABCD"), "book", 10f))