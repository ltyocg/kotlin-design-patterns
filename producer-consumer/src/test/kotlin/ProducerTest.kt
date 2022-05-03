import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.assertTimeout
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import java.time.Duration
import kotlin.test.Test

class ProducerTest {
    @Test
    fun produce() = assertTimeout(Duration.ofMillis(6000)) {
        runBlocking {
            val queue = mock<Channel<Item>>()
            val producer = Producer("producer", queue)
            producer.produce()
            verify(queue).send(any())
            verifyNoMoreInteractions(queue)
        }
    }
}