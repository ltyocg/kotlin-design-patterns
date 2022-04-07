import org.slf4j.LoggerFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

private val log = LoggerFactory.getLogger("main")
fun main() = try {
    val msgQueue = MessageQueue()
    log.info("Submitting TaskGenerators and ServiceExecutor threads.")
    with(Executors.newFixedThreadPool(2)) {
        submit(TaskGenerator(msgQueue, 5))
        submit(TaskGenerator(msgQueue, 1))
        submit(TaskGenerator(msgQueue, 2))
        submit(ServiceExecutor(msgQueue))
        log.info("Initiating shutdown. Executor will shutdown only after all the Threads are completed.")
        shutdown()
        if (!awaitTermination(15, TimeUnit.SECONDS)) {
            log.info("Executor was shut down and Exiting.")
            shutdownNow()
        }
    }
} catch (e: Exception) {
    log.error(e.message)
}