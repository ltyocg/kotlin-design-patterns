package com.ltyocg.hexagonal.domain

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class LotteryTicketIdTest {
    @Test
    fun `test equals`() {
        val ticketId1 = LotteryTicketId()
        val ticketId2 = LotteryTicketId()
        assertNotEquals(ticketId1, ticketId2)
        assertNotEquals(ticketId2, LotteryTicketId())
        assertEquals(ticketId1, LotteryTicketId(ticketId1.id))
    }
}