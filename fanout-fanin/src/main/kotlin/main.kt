import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

private val logger = KotlinLogging.logger {}
fun main() {
    val numbers = listOf(1L, 3L, 4L, 7L, 8L)
    logger.info { "Numbers to be squared and get sum --> $numbers" }
    logger.info { "Sum of all squared numbers --> ${fanOutFanIn(numbers.map(::SquareNumberRequest), Consumer(0L))}" }
}

fun fanOutFanIn(requests: List<SquareNumberRequest>, consumer: Consumer): Long {
    Executors.newFixedThreadPool(requests.size).asCoroutineDispatcher().use { coroutineDispatcher ->
        runBlocking(coroutineDispatcher) {
            requests.forEach { launch { it.delayedSquaring(consumer) } }
        }
    }
    return consumer.sumOfSquaredNumbers.get()
}
