package corruption.system.modern

import corruption.system.AntiCorruptionLayer
import corruption.system.ShopException
import org.springframework.stereotype.Service

@Service
class ModernShop(
    private val store: ModernStore,
    private val acl: AntiCorruptionLayer
) {
    fun placeOrder(order: ModernOrder) {
        val legacyOrder = acl.findOrderInLegacySystem(order.id)
        if (legacyOrder == null) store[order.id] = order
        else if (order != legacyOrder) throw ShopException(legacyOrder.toString(), order.toString())
    }

    fun findOrder(orderId: String): ModernOrder? = store[orderId]
}
