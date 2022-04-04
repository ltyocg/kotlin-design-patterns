import com.ltyocg.commons.assertLogContains
import kotlin.test.Test
import kotlin.test.assertEquals

class SimpleTrollTest {
    @Test
    fun `troll actions`() {
        val troll = SimpleTroll()
        assertEquals(10, troll.attackPower)
        assertLogContains("The troll tries to grab you!") {
            troll.attack()
        }
        assertLogContains("The troll shrieks in horror and runs away!") {
            troll.fleeBattle()
        }
    }
}