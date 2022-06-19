package selector

import Creature
import MovementSelector
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import property.Movement
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MovementSelectorTest {
    @Test
    fun movement() {
        val swimmingCreature = mock<Creature>()
        whenever(swimmingCreature.movement).thenReturn(Movement.SWIMMING)
        val flyingCreature = mock<Creature>()
        whenever(flyingCreature.movement).thenReturn(Movement.FLYING)
        val swimmingSelector = MovementSelector(Movement.SWIMMING)
        assertTrue(swimmingSelector(swimmingCreature))
        assertFalse(swimmingSelector(flyingCreature))
    }
}