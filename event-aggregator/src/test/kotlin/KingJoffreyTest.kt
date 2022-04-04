import com.ltyocg.commons.assertLogContains
import kotlin.test.Test

class KingJoffreyTest {
    @Test
    fun `test onEvent`() {
        val kingJoffrey = KingJoffrey()
        Event.values().forEach {
            assertLogContains("Received event from the King's Hand: $it") {
                kingJoffrey.onEvent(it)
            }
        }
    }
}