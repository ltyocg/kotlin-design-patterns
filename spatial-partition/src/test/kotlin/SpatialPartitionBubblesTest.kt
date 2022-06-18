import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class SpatialPartitionBubblesTest {
    @Test
    fun handleCollisionsUsingQt() {
        val b1 = Bubble(10, 10, 1, 3)
        val b2 = Bubble(5, 5, 2, 1)
        val b3 = Bubble(9, 9, 3, 1)
        val b4 = Bubble(8, 8, 4, 2)
        val bubbles = mutableMapOf(
            1 to b1,
            2 to b2,
            3 to b3,
            4 to b4
        )
        SpatialPartitionBubbles(bubbles, QuadTree(Rect(10.0, 10.0, 20.0, 20.0), 4).apply {
            insert(b1)
            insert(b2)
            insert(b3)
            insert(b4)
        }).handleCollisionsUsingQt(b1)
        assertNull(bubbles[1])
        assertNotNull(bubbles[2])
        assertNull(bubbles[3])
        assertNull(bubbles[4])
    }
}