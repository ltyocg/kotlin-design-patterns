package health.check

import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.Status
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@ExtendWith(MockitoExtension::class)
class MemoryHealthIndicatorTest {
    @Mock
    private lateinit var asynchronousHealthChecker: AsynchronousHealthChecker

    @InjectMocks
    private lateinit var memoryHealthIndicator: MemoryHealthIndicator

    @Test
    fun `when memory usage is below threshold, then health is up`() {
        whenever(asynchronousHealthChecker.performCheck(any(), any()))
            .thenReturn(CompletableFuture.completedFuture(Health.up().withDetail("memory usage", "50% of max").build()))
        val health = memoryHealthIndicator.health()
        assertEquals(Status.UP, health.status)
        assertEquals("50% of max", health.details["memory usage"])
    }

    @Test
    fun `when memory usage is above threshold, then health is down`() {
        whenever(asynchronousHealthChecker.performCheck(any(), any()))
            .thenReturn(CompletableFuture.completedFuture(Health.down().withDetail("memory usage", "95% of max").build()))
        val health = memoryHealthIndicator.health()
        assertEquals(Status.DOWN, health.status)
        assertEquals("95% of max", health.details["memory usage"])
    }

    @Test
    fun `when health check is interrupted, then health is down`() {
        val future = mock<CompletableFuture<Health>>()
        whenever(asynchronousHealthChecker.performCheck(any(), any())).thenReturn(future)
        whenever(future.get()).thenThrow(InterruptedException("Health check interrupted"))
        val health = memoryHealthIndicator.health()
        assertEquals(Status.DOWN, health.status)
        val errorDetail = health.details["error"] as String?
        assertNotNull(errorDetail)
        assertTrue(errorDetail.contains("Health check interrupted"))
    }

    @Test
    fun `when health check execution fails, then health is down`() {
        val future = CompletableFuture<Health>()
        future.completeExceptionally(ExecutionException(RuntimeException("Service unavailable")))
        whenever(asynchronousHealthChecker.performCheck(any(), any())).thenReturn(future)
        val health = memoryHealthIndicator.health()
        assertEquals(Status.DOWN, health.status)
        assertTrue(health.details["error"].toString().contains("Service unavailable"))
    }
}
