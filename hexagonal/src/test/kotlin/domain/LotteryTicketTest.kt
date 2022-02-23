package domain

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class LotteryTicketTest {
    @Test
    fun `test equals`() {
        val ticket1 = LotteryTicket(LotteryTicketId(), PlayerDetails("bob@foo.bar", "1212-121212", "+34332322"), LotteryNumbers.create(setOf(1, 2, 3, 4)))
        assertEquals(ticket1, LotteryTicket(LotteryTicketId(), PlayerDetails("bob@foo.bar", "1212-121212", "+34332322"), LotteryNumbers.create(setOf(1, 2, 3, 4))))
        assertNotEquals(ticket1, LotteryTicket(LotteryTicketId(), PlayerDetails("elsa@foo.bar", "1223-121212", "+49332322"), LotteryNumbers.create(setOf(1, 2, 3, 8))))
    }
}