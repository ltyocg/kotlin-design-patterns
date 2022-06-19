package selector

import Creature
import MassSmallerThanOrEqSelector
import MovementSelector
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import property.Mass
import property.Movement
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CompositeSelectorsTest {
    @Test
    fun `and composition`() {
        val swimmingHeavyCreature = mock<Creature>()
        whenever(swimmingHeavyCreature.movement).thenReturn(Movement.SWIMMING)
        whenever(swimmingHeavyCreature.mass).thenReturn(Mass(100.0))
        val swimmingLightCreature = mock<Creature>()
        whenever(swimmingLightCreature.movement).thenReturn(Movement.SWIMMING)
        whenever(swimmingLightCreature.mass).thenReturn(Mass(25.0))
        val lightAndSwimmingSelector = MassSmallerThanOrEqSelector(50.0) and MovementSelector(Movement.SWIMMING)
        assertFalse(lightAndSwimmingSelector(swimmingHeavyCreature))
        assertTrue(lightAndSwimmingSelector(swimmingLightCreature))
    }

    @Test
    fun `or composition`() {
        val swimmingHeavyCreature = mock<Creature>()
        whenever(swimmingHeavyCreature.movement).thenReturn(Movement.SWIMMING)
        whenever(swimmingHeavyCreature.mass).thenReturn(Mass(100.0))
        val swimmingLightCreature = mock<Creature>()
        whenever(swimmingLightCreature.movement).thenReturn(Movement.SWIMMING)
        whenever(swimmingLightCreature.mass).thenReturn(Mass(25.0))
        val lightOrSwimmingSelector = MassSmallerThanOrEqSelector(50.0) or MovementSelector(Movement.SWIMMING)
        assertTrue(lightOrSwimmingSelector(swimmingHeavyCreature))
        assertTrue(lightOrSwimmingSelector(swimmingLightCreature))
    }

    @Test
    fun `not composition`() {
        val swimmingHeavyCreature = mock<Creature>()
        whenever(swimmingHeavyCreature.movement).thenReturn(Movement.SWIMMING)
        whenever(swimmingHeavyCreature.mass).thenReturn(Mass(100.0))
        val swimmingLightCreature = mock<Creature>()
        whenever(swimmingLightCreature.movement).thenReturn(Movement.SWIMMING)
        whenever(swimmingLightCreature.mass).thenReturn(Mass(25.0))
        val heavySelector = !MassSmallerThanOrEqSelector(50.0)
        assertTrue(heavySelector(swimmingHeavyCreature))
        assertFalse(heavySelector(swimmingLightCreature))
    }
}