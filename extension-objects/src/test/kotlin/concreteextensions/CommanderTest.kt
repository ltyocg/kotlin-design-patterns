package concreteextensions

import Commander
import CommanderUnits
import com.ltyocg.commons.assertLogContains
import org.slf4j.event.Level
import kotlin.test.Test

class CommanderTest {
    @Test
    fun commanderReady() {
        val commander = Commander(CommanderUnits("CommanderUnitsTest"))
        assertLogContains(Level.INFO, "[Commander] ${commander.units.name} is ready!") {
            commander.commanderReady()
        }
    }
}