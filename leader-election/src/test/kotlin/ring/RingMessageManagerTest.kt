package ring

import AbstractInstance
import Message
import MessageType
import RingInstance
import RingMessageManager
import com.ltyocg.commons.FieldAccessor
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RingMessageManagerTest {
    @Test
    fun `test sendHeartbeatMessage`() = assertTrue(RingMessageManager(mapOf(1 to RingInstance(null, 1, 1))).sendHeartbeatMessage(1))

    @Test
    fun `test sendElectionMessage`() {
        val messageContent = "2"
        val instance3 = RingInstance(null, 1, 3)
        RingMessageManager(
            mapOf(
                1 to RingInstance(null, 1, 1),
                2 to RingInstance(null, 1, 2),
                3 to instance3
            )
        ).sendElectionMessage(2, messageContent)
        val ringMessage = Message(MessageType.ELECTION, messageContent)
        val ringMessageSent = FieldAccessor(instance3, AbstractInstance::class).get<Queue<Message>>("messageQueue").poll()
        assertEquals(ringMessageSent.type, ringMessage.type)
        assertEquals(ringMessageSent.content, ringMessage.content)
    }

    @Test
    fun `test sendLeaderMessage`() {
        val instance3 = RingInstance(null, 1, 3)
        RingMessageManager(
            mapOf(
                1 to RingInstance(null, 1, 1),
                2 to RingInstance(null, 1, 2),
                3 to instance3
            )
        ).sendLeaderMessage(2, 3)
        assertEquals(FieldAccessor(instance3, AbstractInstance::class).get<Queue<Message>>("messageQueue").poll(), Message(MessageType.LEADER, "3"))
    }

    @Test
    fun `test sendHeartbeatInvokeMessage`() {
        val instance3 = RingInstance(null, 1, 3)
        RingMessageManager(
            mapOf(
                1 to RingInstance(null, 1, 1),
                2 to RingInstance(null, 1, 2),
                3 to instance3,
            )
        ).sendHeartbeatInvokeMessage(2)
        val ringMessage = Message(MessageType.HEARTBEAT_INVOKE, "")
        val ringMessageSent = FieldAccessor(instance3, AbstractInstance::class).get<Queue<Message>>("messageQueue").poll()
        assertEquals(ringMessageSent.type, ringMessage.type)
        assertEquals(ringMessageSent.content, ringMessage.content)
    }
}