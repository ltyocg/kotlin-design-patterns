package paymentservice

import Service
import generateId

class PaymentService(db: PaymentDatabase, vararg exc: Exception) : Service<PaymentService.PaymentRequest>(db, *exc) {
    override fun receiveRequest(vararg parameters: Any): String? = updateDb(PaymentRequest(generateId(), parameters[0] as Float))
    override fun updateDb(vararg parameters: PaymentRequest): String? {
        val req = parameters[0]
        if (database.get(req.transactionId) == null || !req.paid) {
            database.add(req)
            req.paid = true
            return req.transactionId
        }
        return null
    }

    data class PaymentRequest(
        val transactionId: String,
        val payment: Float,
        var paid: Boolean = false
    )
}