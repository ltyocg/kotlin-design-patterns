package bully

import AbstractInstance
import BullyInstance
import BullyMessageManager
import Message
import MessageType
import com.ltyocg.commons.FieldAccessor
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BullyMessageManagerTest {
    @Test
    fun `test sendHeartbeatMessage`() =
        assertTrue(BullyMessageManager(mapOf(1 to BullyInstance(null, 1, 1))).sendHeartbeatMessage(1))

    @Test
    fun `test sendElectionMessage not accepted`() = assertTrue(
        BullyMessageManager(
            mapOf(
                1 to BullyInstance(null, 1, 1).apply { alive = false },
                2 to BullyInstance(null, 1, 2),
                3 to BullyInstance(null, 1, 3),
                4 to BullyInstance(null, 1, 4)
            )
        ).sendElectionMessage(2, "2")
    )

    @Test
    fun `test sendLeaderMessage`() {
        val instance3 = BullyInstance(null, 1, 3)
        val instance4 = BullyInstance(null, 1, 4)
        BullyMessageManager(
            mapOf(
                1 to BullyInstance(null, 1, 1).apply { alive = false },
                2 to BullyInstance(null, 1, 2),
                3 to instance3,
                4 to instance4
            )
        ).sendLeaderMessage(2, 2)
        val expectedMessage = Message(MessageType.LEADER, "2")
        assertEquals(expectedMessage, FieldAccessor(instance3, AbstractInstance::class).get<Queue<Message>>("messageQueue").poll())
        assertEquals(expectedMessage, FieldAccessor(instance4, AbstractInstance::class).get<Queue<Message>>("messageQueue").poll())
    }

    @Test
    fun `test sendHeartbeatInvokeMessage`() {
        val instance3 = BullyInstance(null, 1, 3)
        BullyMessageManager(
            mapOf(
                1 to BullyInstance(null, 1, 1),
                2 to BullyInstance(null, 1, 2),
                3 to instance3,
            )
        ).sendHeartbeatInvokeMessage(2)
        val message = Message(MessageType.HEARTBEAT_INVOKE, "")
        val messageSent = FieldAccessor(instance3, AbstractInstance::class).get<Queue<Message>>("messageQueue").poll()
        assertEquals(messageSent.type, message.type)
        assertEquals(messageSent.content, message.content)
    }
}