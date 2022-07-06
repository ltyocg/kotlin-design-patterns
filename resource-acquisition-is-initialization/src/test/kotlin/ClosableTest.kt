import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.formattedList
import org.junit.jupiter.api.Test
import kotlin.test.assertContains

class ClosableTest {
    @Test
    fun `open close`() {
        val assertListAppender = assertListAppender(SlidingDoor::class, TreasureChest::class)
        SlidingDoor().use {
            TreasureChest().use {
                assertContains(assertListAppender.formattedList(), "Sliding door opens.")
                assertContains(assertListAppender.formattedList(), "Treasure chest opens.")
            }
        }
        assertContains(assertListAppender.formattedList(), "Treasure chest closes.")
        assertContains(assertListAppender.formattedList(), "Sliding door closes.")
    }
}