import org.mockito.kotlin.reset
import org.mockito.kotlin.spy
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import kotlin.test.Test

class ConsumerTest {
    private val itemCount = 5

    @Test
    fun consume() {
        val queue = spy(ItemQueue())
        repeat(itemCount) {
            queue.put(Item("producer", it))
        }
        reset(queue)
        val consumer = Consumer("consumer", queue)
        repeat(itemCount) {
            consumer.consume()
        }
        verify(queue, times(itemCount)).take()
    }
}