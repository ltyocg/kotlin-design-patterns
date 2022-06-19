import kotlin.test.Test
import kotlin.test.assertEquals

class MonitoringServiceTest {
    @Test
    fun `local response`() =
        assertEquals(MonitoringService(null, null).localResourceResponse(), "Local Service is working")

    @Test
    fun `delayedRemoteResponse success`() = assertEquals(
        MonitoringService(
            CircuitBreaker(DelayedRemoteService(System.nanoTime() - 2 * 1000 * 1000 * 1000, 2), 1, 2L * 1000 * 1000 * 1000),
            null
        ).delayedServiceResponse(),
        "Delayed service is working"
    )

    @Test
    fun `delayedRemoteResponse failure`() = assertEquals(
        MonitoringService(
            CircuitBreaker(DelayedRemoteService(System.nanoTime(), 2), 1, 2L * 1000 * 1000 * 1000),
            null
        ).delayedServiceResponse(),
        "Delayed service is down"
    )

    @Test
    fun `QuickRemoteService response`() = assertEquals(
        MonitoringService(
            CircuitBreaker(QuickRemoteService, 1, 2L * 1000 * 1000 * 1000),
            null
        ).delayedServiceResponse(),
        "Quick Service is working"
    )
}