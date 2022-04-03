import org.slf4j.LoggerFactory

class Worker(private val queueManager: QueueManager) {
    private val log = LoggerFactory.getLogger(javaClass)
    fun run() {
        while (true) {
            val message = queueManager.receiveMessage()
            if (message == null) {
                log.info("No Message ... waiting")
                Thread.sleep(200)
            } else log.info(message.toString())
        }
    }
}