import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.channels.Channel

class GuardedQueue {
    private val logger = KotlinLogging.logger {}
    private val sourceList = Channel<Int>(Channel.Factory.BUFFERED)
    suspend fun get(): Int = try {
        sourceList.receive()
    } finally {
        logger.info { "getting" }
    }

    suspend fun put(e: Int) {
        logger.info { "putting" }
        sourceList.send(e)
        logger.info { "notifying" }
    }
}
