import kotlin.test.Test
import kotlin.test.assertFailsWith

class PoisonMessageTest {
    @Test
    fun addHeader() {
        assertFailsWith<UnsupportedOperationException> {
            Message.POISON_PILL.addHeader(Message.Headers.SENDER, "sender")
        }
    }

    @Test
    fun getHeader() {
        assertFailsWith<UnsupportedOperationException> {
            Message.POISON_PILL.getHeader(Message.Headers.SENDER)
        }
    }

    @Test
    fun headers() {
        assertFailsWith<UnsupportedOperationException> {
            Message.POISON_PILL.headers
        }
    }

    @Test
    fun body() {
        assertFailsWith<UnsupportedOperationException> {
            Message.POISON_PILL.body = "Test message."
        }
        assertFailsWith<UnsupportedOperationException> {
            Message.POISON_PILL.body
        }
    }
}
