package com.ltyocg.extension.objects.concreteextensions

import com.ltyocg.commons.assertLogContains
import com.ltyocg.extension.objects.Commander
import com.ltyocg.extension.objects.CommanderUnits
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