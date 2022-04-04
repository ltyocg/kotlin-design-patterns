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
        val queue = mock<ItemQueue>()
        val producer = Producer("producer", queue)
        producer.produce()
        verify(queue).put(any())
        verifyNoMoreInteractions(queue)
    }
}