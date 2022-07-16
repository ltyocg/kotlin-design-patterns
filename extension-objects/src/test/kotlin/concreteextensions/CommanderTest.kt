package concreteextensions

import Commander
import CommanderUnits
import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.lastMessage
import kotlin.test.Test
import kotlin.test.assertEquals

class CommanderTest {
    @Test
    fun commanderReady() {
        val commander = Commander(CommanderUnits("CommanderUnitsTest"))
        val assertListAppender = assertListAppender(Commander::class)
        commander.commanderReady()
        assertEquals("[Commander] ${commander.units.name} is ready!", assertListAppender.lastMessage())
    }
}