import kotlinx.coroutines.channels.Channel
import org.slf4j.LoggerFactory

class GuardedQueue {
    private val log = LoggerFactory.getLogger(javaClass)
    private val sourceList = Channel<Int>(Channel.Factory.BUFFERED)
    suspend fun get(): Int = sourceList.receive()
        .also { log.info("getting") }

    suspend fun put(e: Int) {
        log.info("putting")
        sourceList.send(e)
        log.info("notifying")
    }
}