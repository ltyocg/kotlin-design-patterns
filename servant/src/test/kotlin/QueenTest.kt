import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class QueenTest {
    @Test
    fun `not flirty uncomplemented`() {
        val queen = Queen()
        queen.setFlirtiness(false)
        queen.changeMood()
        assertFalse(queen.mood)
    }

    @Test
    fun `not flirty complemented`() {
        val queen = Queen()
        queen.setFlirtiness(false)
        queen.receiveCompliments()
        queen.changeMood()
        assertFalse(queen.mood)
    }

    @Test
    fun `flirty uncomplemented`() {
        val queen = Queen()
        queen.changeMood()
        assertFalse(queen.mood)
    }

    @Test
    fun `flirty complemented`() {
        val queen = Queen()
        queen.receiveCompliments()
        queen.changeMood()
        assertTrue(queen.mood)
    }
}