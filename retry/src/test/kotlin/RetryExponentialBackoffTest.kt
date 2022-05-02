import kotlin.reflect.full.isSuperclassOf
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class RetryExponentialBackoffTest {
    @Test
    fun errors() {
        val e = BusinessException("unhandled")
        val retry = RetryExponentialBackoff({ throw e }, 2, 0)
        try {
            retry.perform()
        } catch (_: BusinessException) {
        }
        assertContains(retry.errors(), e)
    }

    @Test
    fun attempts() {
        val retry = RetryExponentialBackoff({ throw BusinessException("unhandled") }, 2, 0)
        try {
            retry.perform()
        } catch (_: BusinessException) {
        }
        assertEquals(retry.attempts(), 1)
    }

    @Test
    fun ignore() {
        val retry = RetryExponentialBackoff(
            { throw CustomerNotFoundException("customer not found") },
            2,
            0,
            { CustomerNotFoundException::class.isSuperclassOf(it::class) }
        )
        try {
            retry.perform()
        } catch (_: BusinessException) {
        }
        assertEquals(retry.attempts(), 2)
    }
}