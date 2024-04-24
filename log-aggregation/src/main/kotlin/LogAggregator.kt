import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

class LogAggregator(
    private val centralLogStore: CentralLogStore,
    private val minLogLevel: LogLevel?
) {
    private val logger = KotlinLogging.logger {}
    private val buffer = ConcurrentLinkedQueue<LogEntry>()
    private val executorService = Executors.newSingleThreadExecutor()
    private val logCount = AtomicInteger(0)

    init {
        startBufferFlusher()
    }

    fun collectLog(logEntry: LogEntry) {
        if (logEntry.level == null || minLogLevel == null) {
            logger.warn { "Log level or threshold level is null. Skipping." }
            return
        }
        if (logEntry.level < minLogLevel) {
            logger.debug { "Log level below threshold. Skipping." }
            return
        }
        buffer.offer(logEntry)
        if (logCount.incrementAndGet() >= 3) {
            flushBuffer()
        }
    }

    fun stop() {
        executorService.shutdownNow()
        if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
            logger.error { "Log aggregator did not terminate." }
        }
        flushBuffer()
    }

    private fun flushBuffer() {
        var logEntry: LogEntry?
        while ((buffer.poll().also { logEntry = it }) != null) {
            centralLogStore.storeLog(logEntry)
            logCount.decrementAndGet()
        }
    }

    private fun startBufferFlusher() = executorService.execute {
        while (!Thread.currentThread().isInterrupted) {
            try {
                Thread.sleep(5000)
                flushBuffer()
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }
    }
}
