package concreteextensions

import Sergeant
import SergeantUnits
import com.ltyocg.commons.assertLogContains
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