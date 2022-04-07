import kotlin.test.Test
import kotlin.test.assertEquals

class MessageTest {
    @Test
    fun test() {
        val testMsg = "Message Test"
        assertEquals(testMsg, Message(testMsg).msg)
    }
}