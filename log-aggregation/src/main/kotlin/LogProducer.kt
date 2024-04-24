import io.github.oshai.kotlinlogging.KotlinLogging
import java.time.LocalDateTime

class LogProducer(
    private val serviceName: String,
    private val aggregator: LogAggregator
) {
    private val logger = KotlinLogging.logger {}
    fun generateLog(level: LogLevel, message: String) {
        val logEntry = LogEntry(serviceName, level, message, LocalDateTime.now())
        logger.info { "Producing log: ${logEntry.message}" }
        aggregator.collectLog(logEntry)
    }
}
