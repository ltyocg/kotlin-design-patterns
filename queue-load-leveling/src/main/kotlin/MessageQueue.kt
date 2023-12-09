import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.concurrent.ArrayBlockingQueue

class MessageQueue {
    private val logger = KotlinLogging.logger {}
    private val blkQueue = ArrayBlockingQueue<Message>(1024)
    fun submitMsg(msg: Message?) {
        try {
            if (msg != null) blkQueue.add(msg)
        } catch (e: Exception) {
            logger.error { e.message }
        }
    }

    fun retrieveMsg(): Message? = try {
        blkQueue.poll()
    } catch (e: Exception) {
        logger.error { e.message }
        null
    }
}
