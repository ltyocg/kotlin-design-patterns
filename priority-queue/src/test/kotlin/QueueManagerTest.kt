import kotlin.test.Test
import kotlin.test.assertEquals

class QueueManagerTest {
    @Test
    fun publishMessage() {
        val queueManager = QueueManager(2)
        val testMessage = Message("Test Message", 1)
        queueManager.publishMessage(testMessage)
        assertEquals(testMessage, queueManager.receiveMessage())
    }

    @Test
    fun receiveMessage() {
        val queueManager = QueueManager(2)
        queueManager.publishMessage(Message("Test Message 1", 1))
        val testMessage2 = Message("Test Message 2", 2)
        queueManager.publishMessage(testMessage2)
        assertEquals(testMessage2, queueManager.receiveMessage())
    }
}