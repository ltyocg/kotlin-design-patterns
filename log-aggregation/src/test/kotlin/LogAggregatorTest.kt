import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class LogAggregatorTest {
    @Mock
    private lateinit var centralLogStore: CentralLogStore
    private lateinit var logAggregator: LogAggregator

    @BeforeEach
    fun setUp() {
        logAggregator = LogAggregator(centralLogStore, LogLevel.INFO)
    }

    @Test
    fun `when three info logs are collected, then central log store should store all of them`() {
        logAggregator.collectLog(createLogEntry(LogLevel.INFO, "Sample log message 1"))
        logAggregator.collectLog(createLogEntry(LogLevel.INFO, "Sample log message 2"))
        verifyNoInteractionsWithCentralLogStore()
        logAggregator.collectLog(createLogEntry(LogLevel.INFO, "Sample log message 3"))
        verify(centralLogStore, times(3)).storeLog(any())
    }

    @Test
    fun `when debug log is collected, then no logs should be stored`() {
        logAggregator.collectLog(createLogEntry(LogLevel.DEBUG, "Sample debug log message"))
        verifyNoInteractionsWithCentralLogStore()
    }

    private fun verifyNoInteractionsWithCentralLogStore() {
        verify(centralLogStore, times(0)).storeLog(any())
    }


    private fun createLogEntry(logLevel: LogLevel, message: String): LogEntry =
        LogEntry("ServiceA", logLevel, message, LocalDateTime.now())
}
