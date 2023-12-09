import io.github.oshai.kotlinlogging.KotlinLogging
import java.security.SecureRandom
import kotlin.system.measureTimeMillis

private val logger = KotlinLogging.logger {}
fun main() {
    val rand = SecureRandom()
    val bubbles1 = buildMap {
        repeat(10000) {
            val b = Bubble(rand.nextInt(300), rand.nextInt(300), it, rand.nextInt(2) + 1)
            put(it, b)
            logger.info { "Bubble $it with radius ${b.radius} added at (${b.coordinateX},${b.coordinateY})" }
        }
    }.toMutableMap()
    val bubbles2 = bubbles1.toMutableMap()
    logger.info {
        "Without spatial partition takes ${
            measureTimeMillis {
                noSpatialPartition(20, bubbles1)
            }
        }ms"
    }
    logger.info {
        "With spatial partition takes ${
            measureTimeMillis {
                withSpatialPartition(300, 300, 20, bubbles2)
            }
        }ms"
    }
}

private fun noSpatialPartition(numOfMovements: Int, bubbles: MutableMap<Int, Bubble>) {
    var n = numOfMovements
    val bubblesToCheck = bubbles.values
    while (n-- > 0 && bubbles.isNotEmpty())
        bubbles.forEach { (i, bubble) ->
            bubble.move()
            bubbles.replace(i, bubble)
            bubble.handleCollision(bubblesToCheck, bubbles)
        }
    bubbles.keys.forEach { logger.info { "Bubble $it not popped" } }
}

private fun withSpatialPartition(height: Int, width: Int, numOfMovements: Int, bubbles: MutableMap<Int, Bubble>) {
    var n = numOfMovements
    val quadTree = QuadTree(Rect(width / 2.0, height / 2.0, width.toDouble(), height.toDouble()), 4)
    while (n-- > 0 && bubbles.isNotEmpty()) {
        bubbles.values.forEach(quadTree::insert)
        bubbles.forEach { (i, bubble) ->
            bubble.move()
            bubbles.replace(i, bubble)
            SpatialPartitionBubbles(bubbles, quadTree).handleCollisionsUsingQt(bubble)
        }
    }
    bubbles.keys.forEach { logger.info { "Bubble $it not popped" } }
}
