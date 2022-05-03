import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import org.mockito.kotlin.reset
import org.mockito.kotlin.spy
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import kotlin.test.Test

class ConsumerTest {
    private val itemCount = 5

    @Test
    fun consume() {
        runBlocking {
            val queue = spy(Channel<Item>(Channel.UNLIMITED))
            repeat(itemCount) {
                queue.send(Item("producer", it))
            }
            reset(queue)
            val consumer = Consumer("consumer", queue)
            repeat(itemCount) {
                consumer.consume()
            }
            verify(queue, times(itemCount)).receive()
        }
    }
}