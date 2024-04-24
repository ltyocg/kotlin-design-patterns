package health.check

import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.*
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.Status
import java.util.concurrent.CompletableFuture
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DatabaseTransactionHealthIndicatorTest {
    private val timeoutInSeconds: Long = 4

    @Mock
    private lateinit var healthCheckRepository: HealthCheckRepository

    @Mock
    private lateinit var asynchronousHealthChecker: AsynchronousHealthChecker
    private lateinit var healthIndicator: DatabaseTransactionHealthIndicator

    @BeforeTest
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        healthIndicator = DatabaseTransactionHealthIndicator(healthCheckRepository, asynchronousHealthChecker, timeoutInSeconds)
    }

    @Test
    fun `when database transaction succeeds, then health is up`() {
        whenever(asynchronousHealthChecker.performCheck(any(), eq(timeoutInSeconds))).thenReturn(CompletableFuture.completedFuture(Health.up().build()))
        doNothing().whenever(healthCheckRepository).performTestTransaction()
        assertEquals(Status.UP, healthIndicator.health().status)
    }

    @Test
    fun `when database transaction fails, then health is down`() {
        whenever(asynchronousHealthChecker.performCheck(any(), eq(timeoutInSeconds))).thenReturn(CompletableFuture())
        doThrow(RuntimeException("DB exception")).whenever(healthCheckRepository).performTestTransaction()
        CompletableFuture<Health>().completeExceptionally(RuntimeException("DB exception"))
        assertEquals(Status.DOWN, healthIndicator.health().status)
    }

    @Test
    fun `when health check times out, then health is down`() {
        whenever(asynchronousHealthChecker.performCheck(any(), eq(timeoutInSeconds))).thenReturn(CompletableFuture())
        CompletableFuture<Health>().completeExceptionally(RuntimeException("Simulated timeout"))
        assertEquals(Status.DOWN, healthIndicator.health().status)
    }
}
