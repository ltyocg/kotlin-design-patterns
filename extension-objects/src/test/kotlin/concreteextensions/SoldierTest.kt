package concreteextensions

import Soldier
import SoldierUnits
import com.ltyocg.commons.assertLogContains
import kotlin.test.Test

class SoldierTest {
    @Test
    fun soldierReady() {
        val soldier = Soldier(SoldierUnits("SoldierUnitsTest"))
        assertLogContains("[Soldier] ${soldier.units.name} is ready!") {
            soldier.soldierReady()
        }
    }
}