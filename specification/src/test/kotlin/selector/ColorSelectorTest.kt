package selector

import ColorSelector
import Creature
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import property.Color
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ColorSelectorTest {
    @Test
    fun color() {
        val greenCreature = mock<Creature>()
        whenever(greenCreature.color).thenReturn(Color.GREEN)
        val redCreature = mock<Creature>()
        whenever(redCreature.color).thenReturn(Color.RED)
        val greenSelector = ColorSelector(Color.GREEN)
        assertTrue(greenSelector(greenCreature))
        assertFalse(greenSelector(redCreature))
    }
}