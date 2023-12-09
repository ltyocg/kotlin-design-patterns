import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.channels.Channel

class Consumer(private val name: String, private val queue: Channel<Item>) {
    private val logger = KotlinLogging.logger {}
    suspend fun consume() {
        val (producer, id) = queue.receive()
        logger.info { "Consumer [$name] consume item [$id] produced by [$producer]" }
    }
}
