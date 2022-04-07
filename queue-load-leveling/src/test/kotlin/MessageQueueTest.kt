import kotlin.test.Test
import kotlin.test.assertEquals

class MessageQueueTest {
    @Test
    fun test() {
        val msgQueue = MessageQueue()
        msgQueue.submitMsg(Message("MessageQueue Test"))
        assertEquals("MessageQueue Test", msgQueue.retrieveMsg()?.msg)
    }
}