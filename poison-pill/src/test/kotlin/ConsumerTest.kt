import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.formattedList
import java.time.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertContentEquals

class ConsumerTest {
    @Test
    fun consume() {
        val messages = listOf(
            createMessage("you", "Hello!"),
            createMessage("me", "Hi!"),
            Message.POISON_PILL,
            createMessage("late_for_the_party", "Hello? Anyone here?")
        )
        val assertListAppender = assertListAppender(Consumer::class)
        Consumer("NSA", SimpleMessageQueue(messages.size).apply { messages.forEach(::put) }).consume()
        assertContentEquals(
            listOf(
                "Message [Hello!] from [you] received by [NSA]",
                "Message [Hi!] from [me] received by [NSA]",
                "Consumer NSA receive request to terminate."
            ),
            assertListAppender.formattedList()
        )
    }

    private fun createMessage(sender: String, message: String): Message = SimpleMessage().apply {
        addHeader(Message.Headers.SENDER, sender)
        addHeader(Message.Headers.DATE, LocalDateTime.now().toString())
        body = message
    }
}