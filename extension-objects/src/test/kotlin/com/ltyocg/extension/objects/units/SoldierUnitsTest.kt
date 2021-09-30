package com.ltyocg.extension.objects.units

import com.ltyocg.extension.objects.SoldierUnits
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class SoldierUnitsTest {
    @Test
    fun getUnitExtension() {
        val units = SoldierUnits("SoldierUnitsName")
        assertNotNull(units.getUnitsExtension("SoldierExtension"))
        assertNull(units.getUnitsExtension("SergeantExtension"))
        assertNull(units.getUnitsExtension("CommanderExtension"))
    }
}