import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.concurrent.ConcurrentLinkedQueue

class CentralLogStore {
    private val logger = KotlinLogging.logger {}
    private val logs = ConcurrentLinkedQueue<LogEntry>()
    fun storeLog(logEntry: LogEntry?) {
        if (logEntry == null) {
            logger.error { "Received null log entry. Skipping." }
            return
        }
        logs.offer(logEntry)
    }

    fun displayLogs() {
        logger.info { "----- Centralized Logs -----" }
        logs.forEach { logger.info { "${it.timestamp} [${it.level}] ${it.message}" } }
    }
}
