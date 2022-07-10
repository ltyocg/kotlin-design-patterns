import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.lastMessage
import kotlin.test.Test
import kotlin.test.assertEquals

class KingJoffreyTest {
    @Test
    fun onEvent() {
        val kingJoffrey = KingJoffrey()
        val assertListAppender = assertListAppender(KingJoffrey::class)
        Event.values().forEach {
            kingJoffrey.onEvent(it)
            assertEquals("Received event from the King's Hand: $it", assertListAppender.lastMessage())
        }
    }
}