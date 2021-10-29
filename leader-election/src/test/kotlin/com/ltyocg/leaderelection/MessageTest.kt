package com.ltyocg.leaderelection

import kotlin.test.Test
import kotlin.test.assertEquals

class MessageTest {
    @Test
    fun `test type`() {
        assertEquals(MessageType.HEARTBEAT, Message(MessageType.HEARTBEAT, "").type)
    }

    @Test
    fun `test content`() {
        val content = "test"
        assertEquals(content, Message(MessageType.HEARTBEAT, content).content)
    }
}