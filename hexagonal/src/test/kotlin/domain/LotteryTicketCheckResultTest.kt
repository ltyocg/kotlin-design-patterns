package domain

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class LotteryTicketCheckResultTest {
    @Test
    fun `test equals`() {
        val result1 = LotteryTicketCheckResult(LotteryTicketCheckResult.CheckResult.NO_PRIZE)
        assertEquals(result1, LotteryTicketCheckResult(LotteryTicketCheckResult.CheckResult.NO_PRIZE))
        assertNotEquals(result1, LotteryTicketCheckResult(LotteryTicketCheckResult.CheckResult.WIN_PRIZE, 300000))
    }
}