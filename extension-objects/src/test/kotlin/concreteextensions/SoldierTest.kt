package concreteextensions

import Soldier
import SoldierUnits
import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.lastMessage
import kotlin.test.Test
import kotlin.test.assertEquals

class SoldierTest {
    @Test
    fun soldierReady() {
        val soldier = Soldier(SoldierUnits("SoldierUnitsTest"))
        val assertListAppender = assertListAppender(Soldier::class)
        soldier.soldierReady()
        assertEquals("[Soldier] ${soldier.units.name} is ready!", assertListAppender.lastMessage())
    }
}