import kotlin.test.*

class BubbleTest {
    @Test
    fun move() {
        val b = Bubble(10, 10, 1, 2)
        val initialX = b.coordinateX
        val initialY = b.coordinateY
        b.move()
        assertTrue(b.coordinateX - initialX < 2 && b.coordinateX - initialX > -2)
        assertTrue(b.coordinateY - initialY < 2 && b.coordinateY - initialY > -2)
    }

    @Test
    fun touches() {
        val b1 = Bubble(0, 0, 1, 2)
        assertTrue(b1.touches(Bubble(1, 1, 2, 1)))
        assertFalse(b1.touches(Bubble(10, 10, 3, 1)))
    }

    @Test
    fun pop() {
        val b1 = Bubble(10, 10, 1, 2)
        val bubbles = mutableMapOf(
            1 to b1,
            2 to Bubble(0, 0, 2, 2)
        )
        b1.pop(bubbles)
        assertNull(bubbles[1])
        assertNotNull(bubbles[2])
    }

    @Test
    fun handleCollision() {
        val b1 = Bubble(0, 0, 1, 2)
        val b2 = Bubble(1, 1, 2, 1)
        val b3 = Bubble(10, 10, 3, 1)
        val bubbles = mutableMapOf(
            1 to b1,
            2 to b2,
            3 to b3,
        )
        val bubblesToCheck = mutableListOf(b2, b3)
        b1.handleCollision(bubblesToCheck, bubbles)
        assertNull(bubbles[1])
        assertNull(bubbles[2])
        assertNotNull(bubbles[3])
    }
}