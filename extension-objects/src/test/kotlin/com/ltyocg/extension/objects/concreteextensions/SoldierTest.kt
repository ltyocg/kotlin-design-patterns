package com.ltyocg.extension.objects.concreteextensions

import com.ltyocg.commons.assertLogContains
import com.ltyocg.extension.objects.Soldier
import com.ltyocg.extension.objects.SoldierUnits
import org.slf4j.event.Level
import kotlin.test.Test

class SoldierTest {
    @Test
    fun soldierReady() {
        val soldier = Soldier(SoldierUnits("SoldierUnitsTest"))
        assertLogContains(Level.INFO, "[Soldier] ${soldier.units.name} is ready!") {
            soldier.soldierReady()
        }
    }
}