package com.ltyocg.databus.members

import com.ltyocg.databus.MessageCollectorMember
import com.ltyocg.databus.MessageData
import com.ltyocg.databus.StartingData
import java.time.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MessageCollectorMemberTest {
    @Test
    fun `collect message from message data`() {
        val message = "message"
        val collector = MessageCollectorMember("collector")
        collector.accept(MessageData(message))
        assertTrue(collector.messages.contains(message))
    }

    @Test
    fun `collect ignores message from other data types`() {
        val collector = MessageCollectorMember("collector")
        collector.accept(StartingData(LocalDateTime.now()))
        assertEquals(0, collector.messages.size)
    }
}