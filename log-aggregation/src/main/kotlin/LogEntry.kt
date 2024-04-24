import java.time.LocalDateTime

data class LogEntry(
    val serviceName: String,
    val level: LogLevel?,
    val message: String,
    val timestamp: LocalDateTime
)
