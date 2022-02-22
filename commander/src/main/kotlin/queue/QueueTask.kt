package queue

import Order

class QueueTask(val order: Order, val taskType: TaskType, val messageType: Int) {
    var firstAttemptTime = -1L
    val type: String
        get() = if (taskType != TaskType.MESSAGING) taskType.toString()
        else when (messageType) {
            0 -> "Payment Failure Message"
            1 -> "Payment Error Message"
            else -> "Payment Success Message"
        }

    enum class TaskType {
        MESSAGING, PAYMENT, EMPLOYEE_DB
    }
}