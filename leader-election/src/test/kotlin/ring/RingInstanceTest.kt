package ring

import AbstractInstance
import Message
import MessageType
import RingInstance
import com.ltyocg.commons.FieldAccessor
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RingInstanceTest {
    @Test
    fun `test onMessage`() {
        val bullyMessage = Message(MessageType.HEARTBEAT, "")
        assertEquals(
            bullyMessage, FieldAccessor(
                RingInstance(null, 1, 1).apply { onMessage(bullyMessage) },
                AbstractInstance::class
            ).get<Queue<Message>>("messageQueue").poll()
        )
    }

    @Test
    fun `test alive`() {
        val ringInstance = RingInstance(null, 1, 1)
        FieldAccessor(ringInstance, AbstractInstance::class)["alive"] = false
        assertFalse(ringInstance.alive)
        ringInstance.alive = true
        assertTrue(ringInstance.alive)
    }
}