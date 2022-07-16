package concreteextensions

import Sergeant
import SergeantUnits
import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.lastMessage
import kotlin.test.Test
import kotlin.test.assertEquals

class SergeantTest {
    @Test
    fun sergeantReady() {
        val sergeant = Sergeant(SergeantUnits("SergeantUnitsTest"))
        val assertListAppender = assertListAppender(Sergeant::class)
        sergeant.sergeantReady()
        assertEquals("[Sergeant] ${sergeant.units.name} is ready!", assertListAppender.lastMessage())
    }
}