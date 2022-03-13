import org.slf4j.LoggerFactory
import java.util.*

private val log = LoggerFactory.getLogger("main")
fun main() {
    val star = Star(StarType.SUN, 10000000, 500000)
    log.info(star.toString())
    val states = Stack<StarMemento>().apply {
        repeat(4) {
            add(star.memento)
            star.timePasses()
            log.info(star.toString())
        }
    }
    while (states.isNotEmpty()) {
        star.memento = states.pop()
        log.info(star.toString())
    }
}
