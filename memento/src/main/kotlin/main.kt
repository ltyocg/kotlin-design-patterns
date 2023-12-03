import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.*

private val logger = KotlinLogging.logger {}
fun main() {
    val star = Star(StarType.SUN, 10000000, 500000)
    logger.info { star }
    val states = Stack<StarMemento>().apply {
        repeat(4) {
            add(star.memento)
            star.timePasses()
            logger.info { star }
        }
    }
    while (states.isNotEmpty()) {
        star.memento = states.pop()
        logger.info { star }
    }
}
