package com.ltyocg.commander.shippingservice

import com.ltyocg.commander.Database
import java.util.*

class ShippingDatabase : Database<ShippingService.ShippingRequest>() {
    private val data = Hashtable<String, ShippingService.ShippingRequest>()
    override fun add(obj: ShippingService.ShippingRequest): ShippingService.ShippingRequest? = data.put(obj.transactionId, obj)
    override fun get(id: String): ShippingService.ShippingRequest? = data[id]
}