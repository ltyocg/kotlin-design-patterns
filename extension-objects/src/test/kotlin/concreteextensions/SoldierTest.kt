package concreteextensions

import Soldier
import SoldierUnits
import com.ltyocg.commons.assertLogContains
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