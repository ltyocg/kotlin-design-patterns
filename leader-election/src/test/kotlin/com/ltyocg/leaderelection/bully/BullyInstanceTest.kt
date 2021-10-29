package com.ltyocg.leaderelection.bully

import com.ltyocg.commons.FieldAccessor
import com.ltyocg.leaderelection.AbstractInstance
import com.ltyocg.leaderelection.BullyInstance
import com.ltyocg.leaderelection.Message
import com.ltyocg.leaderelection.MessageType
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BullyInstanceTest {
    @Test
    fun `test onMessage`() {
        val bullyMessage = Message(MessageType.HEARTBEAT, "")
        assertEquals(
            bullyMessage, FieldAccessor(
                BullyInstance(null, 1, 1).apply { onMessage(bullyMessage) },
                AbstractInstance::class
            ).get<Queue<Message>>("messageQueue").poll()
        )
    }

    @Test
    fun `test alive`() {
        val bullyInstance = BullyInstance(null, 1, 1)
        FieldAccessor(bullyInstance, AbstractInstance::class)["alive"] = false
        assertFalse(bullyInstance.alive)
        bullyInstance.alive = true
        assertTrue(bullyInstance.alive)
    }
}