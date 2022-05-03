import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class KingTest {
    @Test
    fun `hungry sober uncomplimented king`() {
        val king = King()
        king.changeMood()
        assertFalse(king.mood)
    }

    @Test
    fun `fed sober uncomplimented king`() {
        val king = King()
        king.getFed()
        king.changeMood()
        assertFalse(king.mood)
    }

    @Test
    fun `hungry drunk uncomplimented king`() {
        val king = King()
        king.getDrink()
        king.changeMood()
        assertFalse(king.mood)
    }

    @Test
    fun `hungry sober complimented king`() {
        val king = King()
        king.receiveCompliments()
        king.changeMood()
        assertFalse(king.mood)
    }

    @Test
    fun `fed drunk uncomplimented king`() {
        val king = King()
        king.getFed()
        king.getDrink()
        king.changeMood()
        assertTrue(king.mood)
    }

    @Test
    fun `fed sober complimented king`() {
        val king = King()
        king.getFed()
        king.receiveCompliments()
        king.changeMood()
        assertFalse(king.mood)
    }

    @Test
    fun `fed drunk complimented king`() {
        val king = King()
        king.getFed()
        king.getDrink()
        king.receiveCompliments()
        king.changeMood()
        assertFalse(king.mood)
    }

    @Test
    fun `hungry drunk complimented king`() {
        val king = King()
        king.getDrink()
        king.receiveCompliments()
        king.changeMood()
        assertFalse(king.mood)
    }
}