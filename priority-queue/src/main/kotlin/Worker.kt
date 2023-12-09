import io.github.oshai.kotlinlogging.KotlinLogging

class Worker(private val queueManager: QueueManager) {
    private val logger = KotlinLogging.logger {}
    fun run() {
        while (true) {
            val message = queueManager.receiveMessage()
            if (message == null) {
                logger.info { "No Message ... waiting" }
                Thread.sleep(200)
            } else logger.info { message }
        }
    }
}
