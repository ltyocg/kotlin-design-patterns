import org.slf4j.LoggerFactory
import java.util.concurrent.ArrayBlockingQueue

class MessageQueue {
    private val log = LoggerFactory.getLogger(javaClass)
    private val blkQueue = ArrayBlockingQueue<Message>(1024)
    fun submitMsg(msg: Message?) {
        try {
            if (msg != null) blkQueue.add(msg)
        } catch (e: Exception) {
            log.error(e.message)
        }
    }

    fun retrieveMsg(): Message? = try {
        blkQueue.poll()
    } catch (e: Exception) {
        log.error(e.message)
        null
    }
}