import kotlinx.coroutines.channels.Channel
import org.slf4j.LoggerFactory

class Consumer(private val name: String, private val queue: Channel<Item>) {
    private val log = LoggerFactory.getLogger(javaClass)
    suspend fun consume() {
        val (producer, id) = queue.receive()
        log.info("Consumer [{}] consume item [{}] produced by [{}]", name, id, producer)
    }
}