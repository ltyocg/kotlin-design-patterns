package units

import Units
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class UnitsTest {
    @Test
    fun `test const get set`() {
        val name = "testName"
        val units = Units(name)
        assertEquals(name, units.name)
        val newName = "newName"
        units.name = newName
        assertEquals(newName, units.name)
        assertNull(units.getUnitsExtension(""))
        assertNull(units.getUnitsExtension("SoldierExtension"))
        assertNull(units.getUnitsExtension("SergeantExtension"))
        assertNull(units.getUnitsExtension("CommanderExtension"))
    }
}