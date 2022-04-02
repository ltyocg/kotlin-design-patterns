import com.ltyocg.commons.assertLogContentEquals
import java.time.LocalDateTime
import kotlin.test.Test

class ConsumerTest {
    @Test
    fun consume() {
        val messages = listOf(
            createMessage("you", "Hello!"),
            createMessage("me", "Hi!"),
            Message.POISON_PILL,
            createMessage("late_for_the_party", "Hello? Anyone here?")
        )
        val queue = SimpleMessageQueue(messages.size).apply { messages.forEach(::put) }
        assertLogContentEquals(
            listOf(
                "Message [Hello!] from [you] received by [NSA]",
                "Message [Hi!] from [me] received by [NSA]",
                "Consumer NSA receive request to terminate."
            )
        ) { Consumer("NSA", queue).consume() }
    }

    private fun createMessage(sender: String, message: String): Message = SimpleMessage().apply {
        addHeader(Message.Headers.SENDER, sender)
        addHeader(Message.Headers.DATE, LocalDateTime.now().toString())
        body = message
    }
}