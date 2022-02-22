package shippingservice

import Service
import generateId

class ShippingService(db: ShippingDatabase, vararg exc: Exception) : Service<ShippingService.ShippingRequest>(db, *exc) {
    override fun receiveRequest(vararg parameters: Any): String? = updateDb(ShippingRequest(generateId(), parameters[0] as String?, parameters[1] as String?))
    override fun updateDb(vararg parameters: ShippingRequest): String? {
        val req = parameters[0]
        if (database.get(req.transactionId) == null) {
            database.add(req)
            return req.transactionId
        }
        return null
    }

    class ShippingRequest(
        val transactionId: String,
        val item: String?,
        val address: String?
    )
}