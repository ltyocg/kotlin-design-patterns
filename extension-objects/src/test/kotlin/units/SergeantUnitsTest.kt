package units

import SergeantUnits
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class SergeantUnitsTest {
    @Test
    fun getUnitExtension() {
        val units = SergeantUnits("SergeantUnitsName")
        assertNull(units.getUnitsExtension("SoldierExtension"))
        assertNotNull(units.getUnitsExtension("SergeantExtension"))
        assertNull(units.getUnitsExtension("CommanderExtension"))
    }
}