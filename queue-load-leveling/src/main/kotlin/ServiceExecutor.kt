import org.slf4j.LoggerFactory

class ServiceExecutor(private val msgQueue: MessageQueue) : () -> Unit {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun invoke() = try {
        while (!Thread.currentThread().isInterrupted) {
            val msg = msgQueue.retrieveMsg()
            log.info(
                if (msg != null) "$msg is served."
                else "Service Executor: Waiting for Messages to serve .. "
            )
            Thread.sleep(1000)
        }
    } catch (e: Exception) {
        log.error(e.message)
    }
}