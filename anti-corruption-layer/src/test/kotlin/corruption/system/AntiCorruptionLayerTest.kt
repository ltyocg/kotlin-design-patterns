package corruption.system

import corruption.App
import corruption.system.legacy.LegacyOrder
import corruption.system.legacy.LegacyShop
import corruption.system.modern.Customer
import corruption.system.modern.ModernOrder
import corruption.system.modern.ModernShop
import corruption.system.modern.Shipment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

@SpringBootTest(classes = [App::class])
class AntiCorruptionLayerTest {
    @Autowired
    private lateinit var legacyShop: LegacyShop

    @Autowired
    private lateinit var modernShop: ModernShop

    @Test
    fun test() {
        val legacyOrder = LegacyOrder("1", "addr1", "item1", 1, 1)
        legacyShop.placeOrder(legacyOrder)
        assertEquals(legacyOrder, legacyShop.findOrder("1"))
        modernShop.placeOrder(ModernOrder("1", Customer("addr1"), Shipment("item1", 1, 1), ""))
        assertNull(modernShop.findOrder("1"))
    }

    @Test
    fun `with ex test`() {
        val legacyOrder = LegacyOrder("1", "addr1", "item1", 1, 1)
        legacyShop.placeOrder(legacyOrder)
        assertEquals(legacyOrder, legacyShop.findOrder("1"))
        assertFailsWith<ShopException> {
            modernShop.placeOrder(ModernOrder("1", Customer("addr1"), Shipment("item1", 10, 1), ""))
        }
    }
}
