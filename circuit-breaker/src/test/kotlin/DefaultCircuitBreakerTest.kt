import kotlin.test.Test
import kotlin.test.assertEquals

class DefaultCircuitBreakerTest {
    @Test
    fun `test evaluateState`() {
        with(DefaultCircuitBreaker(null, 1, 100)) {
            assertEquals(state.name, "CLOSED")
            failureCount = 4
            lastFailureTime = System.nanoTime()
            evaluateState()
            assertEquals(state.name, "HALF_OPEN")
            lastFailureTime = System.nanoTime() - (1000L * 1000 * 1000 * 1000).toInt()
            evaluateState()
            assertEquals(state.name, "OPEN")
            failureCount = 0
            evaluateState()
            assertEquals(state.name, "CLOSED")
        }
    }

    @Test
    fun `test setState for by pass`() {
        with(DefaultCircuitBreaker(null, 1, 2000L * 1000 * 1000)) {
            state = State.OPEN
            assertEquals(state.name, "OPEN")
        }
    }

    @Test
    fun `test api responses`() {
        assertEquals(DefaultCircuitBreaker(object : RemoteService {
            override fun call(): String = "Remote Success"
        }, 1, 100).attemptRequest(), "Remote Success")
    }
}