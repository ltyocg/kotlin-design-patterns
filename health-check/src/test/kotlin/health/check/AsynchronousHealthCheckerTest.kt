package health.check

import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.formattedList
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.Status
import java.util.concurrent.RejectedExecutionException
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import kotlin.test.*

class AsynchronousHealthCheckerTest {
    private lateinit var healthChecker: AsynchronousHealthChecker

    @Mock
    private lateinit var executorService: ScheduledExecutorService

    init {
        MockitoAnnotations.openMocks(this)
    }

    @BeforeTest
    fun setUp() {
        healthChecker = AsynchronousHealthChecker()
    }

    @AfterTest
    fun tearDown() {
        healthChecker.shutdown()
    }

    @Test
    fun `when performCheck, then completes normally`() =
        assertEquals(Health.up().build(), healthChecker.performCheck({ Health.up().build() }, 3).get())

    @Test
    fun `when health check is successful, returns healthy`() =
        assertEquals(Status.UP, healthChecker.performCheck({ Health.up().build() }, 4).get().status)

    @Test
    fun `when shutdown, then rejects new tasks`() {
        healthChecker.shutdown()
        assertFailsWith<RejectedExecutionException>("Expected to throw RejectedExecutionException but did not") {
            healthChecker.performCheck({ Health.up().build() }, 2)
        }
    }

    @Test
    fun `when health check throws exception, then returns down`() {
        val health = healthChecker.performCheck({ throw RuntimeException("Health check failed") }, 10).join()
        assertEquals(Status.DOWN, health.status)
        assertContains(health.details["error"].toString(), "Health check failed")
    }

    @Test
    fun `when shutdown executor does not terminate after canceling, logs error message`() {
        healthChecker.shutdown()
        val assertListAppender = assertListAppender(AsynchronousHealthChecker::class)
        val containsMessage = "Health check executor did not terminate" in assertListAppender.formattedList()
        healthChecker.shutdown()
        assertTrue(containsMessage, "Expected log message not found")
    }

    @Test
    fun `await termination with timeout, incomplete termination, returns true`() {
        whenever(executorService.awaitTermination(5, TimeUnit.SECONDS)).thenReturn(false)
        val privateMethod = AsynchronousHealthChecker::class.java.getDeclaredMethod("awaitTerminationWithTimeout")
        privateMethod.isAccessible = true
        val result = privateMethod.invoke(healthChecker) as Boolean
        assertTrue(result, "Termination should be incomplete")
    }
}
