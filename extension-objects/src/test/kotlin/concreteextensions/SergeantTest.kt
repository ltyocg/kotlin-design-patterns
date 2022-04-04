package concreteextensions

import Sergeant
import SergeantUnits
import com.ltyocg.commons.assertLogContains
import kotlin.test.Test

class SergeantTest {
    @Test
    fun sergeantReady() {
        val sergeant = Sergeant(SergeantUnits("SergeantUnitsTest"))
        assertLogContains("[Sergeant] ${sergeant.units.name} is ready!") {
            sergeant.sergeantReady()
        }
    }
}