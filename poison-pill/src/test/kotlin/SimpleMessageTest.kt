import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SimpleMessageTest {
    @Test
    fun getHeaders() {
        val message = SimpleMessage()
        assertTrue(message.headers.isEmpty())
        val senderName = "test"
        message.addHeader(Message.Headers.SENDER, senderName)
        assertFalse(message.headers.isEmpty())
        assertEquals(senderName, message.headers[Message.Headers.SENDER])
    }
}