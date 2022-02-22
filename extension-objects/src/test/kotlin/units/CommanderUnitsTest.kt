package units

import CommanderUnits
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class CommanderUnitsTest {
    @Test
    fun getUnitExtension() {
        val units = CommanderUnits("CommanderUnitsName")
        assertNull(units.getUnitsExtension("SoldierExtension"))
        assertNull(units.getUnitsExtension("SergeantExtension"))
        assertNotNull(units.getUnitsExtension("CommanderExtension"))
    }
}