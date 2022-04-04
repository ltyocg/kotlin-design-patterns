package concreteextensions

import Commander
import CommanderUnits
import com.ltyocg.commons.assertLogContains
import kotlin.test.Test

class CommanderTest {
    @Test
    fun commanderReady() {
        val commander = Commander(CommanderUnits("CommanderUnitsTest"))
        assertLogContains("[Commander] ${commander.units.name} is ready!") {
            commander.commanderReady()
        }
    }
}