import kotlin.test.Test
import kotlin.test.assertEquals

class AlchemistShopTest {
    @Test
    fun `test shop`() {
        val shop = AlchemistShop()
        val bottomShelf = shop.bottomShelf
        assertEquals(5, bottomShelf.size)
        val topShelf = shop.topShelf
        assertEquals(8, topShelf.size)
        val allPotions = topShelf + bottomShelf
        assertEquals(13, allPotions.size)
        assertEquals(5, allPotions.distinctBy(System::identityHashCode).size)
    }
}