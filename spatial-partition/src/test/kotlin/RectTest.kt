import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RectTest {
    @Test
    fun contains() {
        val r = Rect(10.0, 10.0, 20.0, 20.0)
        assertTrue(Bubble(2, 2, 1, 1) in r)
        assertFalse(Bubble(30, 30, 2, 1) in r)
    }

    @Test
    fun intersects() {
        val r1 = Rect(10.0, 10.0, 20.0, 20.0)
        assertTrue(r1.intersects(Rect(15.0, 15.0, 20.0, 20.0)))
        assertFalse(r1.intersects(Rect(50.0, 50.0, 20.0, 20.0)))
    }
}