package list

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals
import kotlin.test.fail

class TreasureChestTest {
    companion object {
        @JvmStatic
        fun dataProvider(): List<Array<Any>> = listOf(
            arrayOf(Item(ItemType.POTION, "Potion of courage")),
            arrayOf(Item(ItemType.RING, "Ring of shadows")),
            arrayOf(Item(ItemType.POTION, "Potion of wisdom")),
            arrayOf(Item(ItemType.POTION, "Potion of blood")),
            arrayOf(Item(ItemType.WEAPON, "Sword of silver +1")),
            arrayOf(Item(ItemType.POTION, "Potion of rust")),
            arrayOf(Item(ItemType.POTION, "Potion of healing")),
            arrayOf(Item(ItemType.RING, "Ring of armor")),
            arrayOf(Item(ItemType.WEAPON, "Steel halberd")),
            arrayOf(Item(ItemType.WEAPON, "Dagger of poison"))
        )
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    fun testIterator(expectedItem: Item) {
        TreasureChest().iterator(expectedItem.type).forEach {
            assertEquals(expectedItem.type, it.type)
            if (expectedItem.toString() == it.toString()) return
        }
        fail("Expected to find item [$expectedItem] using iterator, but we didn't.")
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    fun testGetItems(expectedItem: Item) {
        TreasureChest().items.forEach {
            if (expectedItem.type === it.type && expectedItem.toString() == it.toString()) return
        }
        fail("Expected to find item [$expectedItem] in the item list, but we didn't.")
    }
}