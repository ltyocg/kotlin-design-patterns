package com.ltyocg.commander

class Order internal constructor(val user: User, val item: String, val price: Float) {
    val id = run {
        var id: String
        do id = generateId()
        while (usedIds[id] == true)
        usedIds[id] = true
        id
    }
    val createdTime = System.currentTimeMillis()
    var paid = PaymentStatus.TRYING
    var messageSent = MessageSent.NONE_SENT
    var addedToEmployeeHandle = false

    companion object {
        private val usedIds = mutableMapOf<String, Boolean>()
    }

    enum class PaymentStatus {
        NOT_DONE, TRYING, DONE
    }

    enum class MessageSent {
        NONE_SENT, PAYMENT_FAIL, PAYMENT_TRYING, PAYMENT_SUCCESSFUL
    }
}