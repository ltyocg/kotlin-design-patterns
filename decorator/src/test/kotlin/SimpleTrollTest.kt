import com.ltyocg.commons.assertLogContains
import org.slf4j.event.Level
import kotlin.test.Test
import kotlin.test.assertEquals

class SimpleTrollTest {
    @Test
    fun `test Troll actions`() {
        val troll = SimpleTroll()
        assertEquals(10, troll.attackPower)
        assertLogContains(Level.INFO, "The troll tries to grab you!") {
            troll.attack()
        }
        assertLogContains(Level.INFO, "The troll shrieks in horror and runs away!") {
            troll.fleeBattle()
        }
    }
}