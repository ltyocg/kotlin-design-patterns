package com.ltyocg.commander

import com.ltyocg.commander.employeehandle.EmployeeDatabase
import com.ltyocg.commander.employeehandle.EmployeeHandle
import com.ltyocg.commander.messagingservice.MessagingDatabase
import com.ltyocg.commander.messagingservice.MessagingService
import com.ltyocg.commander.paymentservice.PaymentDatabase
import com.ltyocg.commander.paymentservice.PaymentService
import com.ltyocg.commander.queue.QueueDatabase
import com.ltyocg.commander.shippingservice.ShippingDatabase
import com.ltyocg.commander.shippingservice.ShippingService
import kotlinx.coroutines.runBlocking

private const val NUM_OF_RETRIES = 3
private const val RETRY_DURATION = 30000L
private const val QUEUE_TIME = 240000L
private const val QUEUE_TASK_TIME = 60000L
private const val PAYMENT_TIME = 120000L
private const val MESSAGE_TIME: Long = 150000L
private const val EMPLOYEE_TIME: Long = 240000L

fun main() = runBlocking {
    Commander(
        EmployeeHandle(EmployeeDatabase()),
        PaymentService(PaymentDatabase(), *Array(6) { DatabaseUnavailableException() }),
        ShippingService(ShippingDatabase()),
        MessagingService(MessagingDatabase(), *Array(2) { DatabaseUnavailableException() }),
        QueueDatabase(),
        NUM_OF_RETRIES,
        RETRY_DURATION,
        QUEUE_TIME,
        QUEUE_TASK_TIME,
        PAYMENT_TIME,
        MESSAGE_TIME,
        EMPLOYEE_TIME
    ).placeOrder(Order(User("Jim", "ABCD"), "book", 10f))
}