import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RectangleTest {
    @Test
    fun `test constructor`() {
        with(Rectangle(1, 2, 3, 4)) {
            assertEquals(1, left)
            assertEquals(2, top)
            assertEquals(3, right)
            assertEquals(4, bottom)
        }
    }

    @Test
    fun `test toString`() {
        assertEquals("[1,2,3,4]", Rectangle(1, 2, 3, 4).toString())
    }

    @Test
    fun `test intersection`() {
        val rectangle = Rectangle(0, 0, 1, 1)
        assertTrue(rectangle.intersectsWith(rectangle))
        assertTrue(rectangle.intersectsWith(Rectangle(-1, -5, 7, 8)))
        assertFalse(rectangle.intersectsWith(Rectangle(2, 2, 3, 3)))
        assertFalse(rectangle.intersectsWith(Rectangle(-2, -2, -1, -1)))
    }
}