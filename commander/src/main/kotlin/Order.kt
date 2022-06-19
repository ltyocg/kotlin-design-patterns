class Order
internal constructor(val user: User, val item: String, val price: Float) {
    val id = run {
        var id: String
        do id = generateId()
        while (!usedIds.add(id))
        id
    }
    val createdTime = System.currentTimeMillis()
    var paid = PaymentStatus.TRYING
    var messageSent = MessageSent.NONE_SENT
    var addedToEmployeeHandle = false

    private companion object {
        private val usedIds = mutableSetOf<String>()
    }

    enum class PaymentStatus {
        NOT_DONE, TRYING, DONE
    }

    enum class MessageSent {
        NONE_SENT, PAYMENT_FAIL, PAYMENT_TRYING, PAYMENT_SUCCESSFUL
    }
}