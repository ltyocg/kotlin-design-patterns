import org.mockito.kotlin.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.fail

class ProducerTest {
    @Test
    fun send() {
        val publishPoint = mock<MqPublishPoint>()
        val producer = Producer("producer", publishPoint)
        verifyNoMoreInteractions(publishPoint)
        producer.send("Hello!")
        val messageCaptor = argumentCaptor<Message>()
        verify(publishPoint).put(messageCaptor.capture())
        val message = messageCaptor.firstValue
        assertEquals("producer", message.getHeader(Message.Headers.SENDER))
        assertNotNull(message.getHeader(Message.Headers.DATE))
        assertEquals("Hello!", message.body)
        verifyNoMoreInteractions(publishPoint)
    }

    @Test
    fun stop() {
        val publishPoint = mock<MqPublishPoint>()
        val producer = Producer("producer", publishPoint)
        verifyNoMoreInteractions(publishPoint)
        producer.stop()
        verify(publishPoint).put(eq(Message.POISON_PILL))
        try {
            producer.send("Hello!")
            fail("Expected 'IllegalStateException' at this point, since the producer has stopped!")
        } catch (e: IllegalStateException) {
            assertNotNull(e.message)
            assertEquals("Producer Hello! was stopped and fail to deliver requested message [producer].", e.message)
        }
        verifyNoMoreInteractions(publishPoint)
    }
}