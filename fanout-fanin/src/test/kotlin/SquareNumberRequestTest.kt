import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class SquareNumberRequestTest {
    @Test
    fun `delayedSquaring test`() = runBlocking {
        val consumer = Consumer(10L)
        SquareNumberRequest(5L).delayedSquaring(consumer)
        assertEquals(35, consumer.sumOfSquaredNumbers.get())
    }
}