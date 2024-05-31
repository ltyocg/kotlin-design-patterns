package corruption.system.legacy

import org.springframework.stereotype.Service

@Service
class LegacyShop(private val store: LegacyStore) {
    fun placeOrder(legacyOrder: LegacyOrder) {
        store[legacyOrder.id] = legacyOrder
    }

    fun findOrder(orderId: String): LegacyOrder? = store[orderId]
}
