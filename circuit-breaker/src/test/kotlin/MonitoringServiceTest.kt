import kotlin.test.Test
import kotlin.test.assertEquals

class MonitoringServiceTest {
    @Test
    fun `test local response`() {
        assertEquals(MonitoringService(null, null).localResourceResponse(), "Local Service is working")
    }

    @Test
    fun `test delayedRemoteResponse success`() {
        assertEquals(
            MonitoringService(
                DefaultCircuitBreaker(DelayedRemoteService(System.nanoTime() - 2 * 1000 * 1000 * 1000, 2), 1, 2L * 1000 * 1000 * 1000),
                null
            ).delayedServiceResponse(),
            "Delayed service is working"
        )
    }

    @Test
    fun `test delayedRemoteResponse failure`() {
        assertEquals(
            MonitoringService(
                DefaultCircuitBreaker(DelayedRemoteService(System.nanoTime(), 2), 1, 2L * 1000 * 1000 * 1000),
                null
            ).delayedServiceResponse(),
            "Delayed service is down"
        )
    }

    @Test
    fun `test QuickRemoteService response`() {
        assertEquals(
            MonitoringService(
                DefaultCircuitBreaker(QuickRemoteService(), 1, 2L * 1000 * 1000 * 1000),
                null
            ).delayedServiceResponse(),
            "Quick Service is working"
        )
    }
}