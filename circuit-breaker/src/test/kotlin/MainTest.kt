import org.slf4j.LoggerFactory
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

private const val STARTUP_DELAY = 4
private const val FAILURE_THRESHOLD = 1
private const val RETRY_PERIOD = 2

class MainTest {
    private val log = LoggerFactory.getLogger(javaClass)
    private lateinit var monitoringService: MonitoringService
    private lateinit var delayedServiceCircuitBreaker: CircuitBreaker
    private lateinit var quickServiceCircuitBreaker: CircuitBreaker

    @BeforeTest
    fun `setup circuit breakers`() {
        delayedServiceCircuitBreaker = DefaultCircuitBreaker(DelayedRemoteService(System.nanoTime(), STARTUP_DELAY), FAILURE_THRESHOLD, RETRY_PERIOD * 1000L * 1000 * 1000)
        quickServiceCircuitBreaker = DefaultCircuitBreaker(QuickRemoteService(), FAILURE_THRESHOLD, RETRY_PERIOD * 1000L * 1000 * 1000)
        monitoringService = MonitoringService(delayedServiceCircuitBreaker, quickServiceCircuitBreaker)
    }

    @Test
    fun `test failure OPEN state transition`() {
        assertEquals("Delayed service is down", monitoringService.delayedServiceResponse())
        assertEquals("OPEN", delayedServiceCircuitBreaker.state.name)
        assertEquals("Delayed service is down", monitoringService.delayedServiceResponse())
        assertEquals("Quick Service is working", monitoringService.quickServiceResponse())
        assertEquals("CLOSED", quickServiceCircuitBreaker.state.name)
    }

    @Test
    fun `test failure HALF_OPEN state transition`() {
        assertEquals("Delayed service is down", monitoringService.delayedServiceResponse())
        assertEquals("OPEN", delayedServiceCircuitBreaker.state.name)
        log.info("Waiting 2s for delayed service to become responsive")
        Thread.sleep(2000)
        assertEquals("HALF_OPEN", delayedServiceCircuitBreaker.state.name)
    }

    @Test
    fun `test recovery CLOSED state transition`() {
        assertEquals("Delayed service is down", monitoringService.delayedServiceResponse())
        assertEquals("OPEN", delayedServiceCircuitBreaker.state.name)
        log.info("Waiting 4s for delayed service to become responsive")
        Thread.sleep(4000)
        assertEquals("HALF_OPEN", delayedServiceCircuitBreaker.state.name)
        assertEquals("Delayed service is working", monitoringService.delayedServiceResponse())
        assertEquals("CLOSED", delayedServiceCircuitBreaker.state.name)
    }
}