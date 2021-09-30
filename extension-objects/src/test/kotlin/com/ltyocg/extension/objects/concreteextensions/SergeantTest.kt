package com.ltyocg.extension.objects.concreteextensions

import com.ltyocg.commons.assertLogContains
import com.ltyocg.extension.objects.Sergeant
import com.ltyocg.extension.objects.SergeantUnits
import org.slf4j.event.Level
import kotlin.test.Test

class SergeantTest {
    @Test
    fun sergeantReady() {
        val sergeant = Sergeant(SergeantUnits("SergeantUnitsTest"))
        assertLogContains(Level.INFO, "[Sergeant] ${sergeant.units.name} is ready!") {
            sergeant.sergeantReady()
        }
    }
}