package paymentservice

import Database
import java.util.*

class PaymentDatabase : Database<PaymentService.PaymentRequest>() {
    private val data = Hashtable<String, PaymentService.PaymentRequest>()
    override fun add(obj: PaymentService.PaymentRequest): PaymentService.PaymentRequest? = data.put(obj.transactionId, obj)
    override fun get(id: String): PaymentService.PaymentRequest? = data[id]
}