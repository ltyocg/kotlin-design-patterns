import com.ltyocg.commons.logContents
import org.junit.jupiter.api.Test
import kotlin.test.assertContains

class ClosableTest {
    @Test
    fun `open close`() {
        logContents {
            SlidingDoor().use {
                TreasureChest().use {
                    assertContains(messageList, "Sliding door opens.")
                    assertContains(messageList, "Treasure chest opens.")
                    clear()
                }
            }
            assertContains(messageList, "Treasure chest closes.")
            assertContains(messageList, "Sliding door closes.")
        }
    }
}