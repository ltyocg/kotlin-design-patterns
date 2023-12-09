import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

private val logger = KotlinLogging.logger {}
fun main() = try {
    val msgQueue = MessageQueue()
    logger.info { "Submitting TaskGenerators and ServiceExecutor threads." }
    with(Executors.newFixedThreadPool(2)) {
        submit(TaskGenerator(msgQueue, 5))
        submit(TaskGenerator(msgQueue, 1))
        submit(TaskGenerator(msgQueue, 2))
        submit(ServiceExecutor(msgQueue))
        logger.info { "Initiating shutdown. Executor will shutdown only after all the Threads are completed." }
        shutdown()
        if (!awaitTermination(15, TimeUnit.SECONDS)) {
            logger.info { "Executor was shut down and Exiting." }
            shutdownNow()
        }
    }
} catch (e: Exception) {
    logger.error { e.message }
}
