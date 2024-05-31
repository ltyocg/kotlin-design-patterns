package corruption.system

import corruption.system.legacy.LegacyShop
import corruption.system.modern.Customer
import corruption.system.modern.ModernOrder
import corruption.system.modern.Shipment
import org.springframework.stereotype.Service

@Service
class AntiCorruptionLayer(private val legacyShop: LegacyShop) {
    fun findOrderInLegacySystem(id: String): ModernOrder? = legacyShop.findOrder(id)?.let {
        ModernOrder(
            it.id,
            Customer(it.customer),
            Shipment(it.item, it.qty, it.price),
            ""
        )
    }
}
