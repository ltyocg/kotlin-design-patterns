package selector

import Creature
import MassSmallerThanOrEqSelector
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import property.Mass
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MassSelectorTest {
    @Test
    fun mass() {
        val lightCreature = mock<Creature>()
        whenever(lightCreature.mass).thenReturn(Mass(50.0))
        val heavyCreature = mock<Creature>()
        whenever(heavyCreature.mass).thenReturn(Mass(2500.0))
        val lightSelector = MassSmallerThanOrEqSelector(500.0)
        assertTrue(lightSelector(lightCreature))
        assertFalse(lightSelector(heavyCreature))
    }
}