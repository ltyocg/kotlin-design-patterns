package shippingservice

import Database
import java.util.*

object ShippingDatabase : Database<ShippingService.ShippingRequest>() {
    private val data = Hashtable<String, ShippingService.ShippingRequest>()
    override fun add(obj: ShippingService.ShippingRequest): ShippingService.ShippingRequest? = data.put(obj.transactionId, obj)
    override fun get(id: String): ShippingService.ShippingRequest? = data[id]
}