import io.github.oshai.kotlinlogging.KotlinLogging

class ServiceExecutor(private val msgQueue: MessageQueue) : () -> Unit {
    private val logger = KotlinLogging.logger {}
    override fun invoke() = try {
        while (!Thread.currentThread().isInterrupted) {
            val msg = msgQueue.retrieveMsg()
            logger.info {
                if (msg != null) "$msg is served."
                else "Service Executor: Waiting for Messages to serve .. "
            }
            Thread.sleep(1000)
        }
    } catch (e: Exception) {
        logger.error { e.message }
    }
}
