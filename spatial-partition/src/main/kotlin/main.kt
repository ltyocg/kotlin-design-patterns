import org.slf4j.LoggerFactory
import java.security.SecureRandom
import kotlin.system.measureTimeMillis

private val log = LoggerFactory.getLogger("main")
fun main() {
    val rand = SecureRandom()
    val bubbles1 = buildMap {
        repeat(10000) {
            val b = Bubble(rand.nextInt(300), rand.nextInt(300), it, rand.nextInt(2) + 1)
            put(it, b)
            log.info("Bubble {} with radius {} added at ({},{})", it, b.radius, b.coordinateX, b.coordinateY)
        }
    }.toMutableMap()
    val bubbles2 = bubbles1.toMutableMap()
    log.info("Without spatial partition takes {}ms", measureTimeMillis {
        noSpatialPartition(20, bubbles1)
    })
    log.info("With spatial partition takes {}ms", measureTimeMillis {
        withSpatialPartition(300, 300, 20, bubbles2)
    })
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
    bubbles.keys.forEach { log.info("Bubble {} not popped", it) }
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
    bubbles.keys.forEach { log.info("Bubble {} not popped", it) }
}