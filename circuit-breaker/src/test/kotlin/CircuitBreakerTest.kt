import kotlin.test.Test
import kotlin.test.assertEquals

class CircuitBreakerTest {
    @Test
    fun evaluateState() {
        with(CircuitBreaker(null, 1, 100)) {
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
    fun `setState for by pass`() {
        with(CircuitBreaker(null, 1, 2000L * 1000 * 1000)) {
            state = CircuitBreaker.State.OPEN
            assertEquals(state.name, "OPEN")
        }
    }

    @Test
    fun `api responses`() =
        assertEquals(CircuitBreaker({ "Remote Success" }, 1, 100).attemptRequest(), "Remote Success")
}