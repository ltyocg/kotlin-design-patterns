import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import java.util.concurrent.Executors

private val log = LoggerFactory.getLogger("main")
fun main() {
    val numbers = listOf(1L, 3L, 4L, 7L, 8L)
    log.info("Numbers to be squared and get sum --> {}", numbers)
    log.info("Sum of all squared numbers --> {}", fanOutFanIn(numbers.map(::SquareNumberRequest), Consumer(0L)))
}

fun fanOutFanIn(requests: List<SquareNumberRequest>, consumer: Consumer): Long {
    Executors.newFixedThreadPool(requests.size).asCoroutineDispatcher().use { coroutineDispatcher ->
        runBlocking(coroutineDispatcher) {
            requests.forEach { launch { it.delayedSquaring(consumer) } }
        }
    }
    return consumer.sumOfSquaredNumbers.get()
}