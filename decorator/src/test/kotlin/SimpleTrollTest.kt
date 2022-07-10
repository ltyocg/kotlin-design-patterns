import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.lastMessage
import kotlin.test.Test
import kotlin.test.assertEquals

class SimpleTrollTest {
    @Test
    fun `troll actions`() {
        val troll = SimpleTroll()
        assertEquals(10, troll.attackPower)
        val assertListAppender = assertListAppender(SimpleTroll::class)
        troll.attack()
        assertEquals("The troll tries to grab you!", assertListAppender.lastMessage())
        troll.fleeBattle()
        assertEquals("The troll shrieks in horror and runs away!", assertListAppender.lastMessage())
    }
}