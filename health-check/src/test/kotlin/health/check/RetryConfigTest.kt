package health.check

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.retry.support.RetryTemplate
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest(classes = [RetryConfig::class])
class RetryConfigTest {
    @Autowired
    private lateinit var retryTemplate: RetryTemplate

    @Test
    fun `should retry three times with two second delay`() {
        val attempts = AtomicInteger()
        val retryableOperation = {
            attempts.incrementAndGet()
            throw RuntimeException("Test exception for retry")
        }
        val elapsedTime = measureTimeMillis {
            try {
                retryTemplate.execute<Nothing, RuntimeException> { retryableOperation() }
            } catch (_: Exception) {
            }
        }
        assertEquals(3, attempts.get(), "Should have retried three times")
        assertTrue(elapsedTime >= 4000, "Should have waited at least 4 seconds in total for backoff")
    }
}
