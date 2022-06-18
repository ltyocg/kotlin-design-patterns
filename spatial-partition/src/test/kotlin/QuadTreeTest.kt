import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

class QuadTreeTest {
    @Test
    fun query() {
        val points = buildList {
            val rand = Random()
            repeat(20) {
                add(Bubble(rand.nextInt(300), rand.nextInt(300), it, rand.nextInt(2) + 1))
            }
        }
        val queryRange = Rect(70.0, 130.0, 100.0, 100.0)
        assertEquals(
            quadTree(points, Rect(150.0, 150.0, 300.0, 300.0), queryRange),
            points.filter { it in queryRange }.associateBy { it.id }
        )
    }

    private fun quadTree(points: Collection<Point<*>>, field: Rect, queryRange: Rect): Map<Int, Point<*>> {
        val qTree = QuadTree(queryRange, 4)
        points.forEach { qTree.insert(it) }
        return qTree.query(field, mutableListOf()).associateBy { it.id }
    }
}