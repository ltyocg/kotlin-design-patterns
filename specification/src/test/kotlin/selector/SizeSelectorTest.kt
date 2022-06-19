package selector

import Creature
import SizeSelector
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import property.Size
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SizeSelectorTest {
    @Test
    fun size() {
        val normalCreature = mock<Creature>()
        whenever(normalCreature.size).thenReturn(Size.NORMAL)
        val smallCreature = mock<Creature>()
        whenever(smallCreature.size).thenReturn(Size.SMALL)
        val normalSelector = SizeSelector(Size.NORMAL)
        assertTrue(normalSelector(normalCreature))
        assertFalse(normalSelector(smallCreature))
    }
}