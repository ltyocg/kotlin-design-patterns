package domain

import LotteryTestUtils
import WireTransfers
import lotteryContext
import org.springframework.beans.factory.getBean
import kotlin.test.*

class LotteryTest {
    private lateinit var administration: LotteryAdministration
    private lateinit var service: LotteryService
    private lateinit var wireTransfers: WireTransfers

    @BeforeTest
    fun setup() {
        administration = lotteryContext.getBean()
        service = lotteryContext.getBean()
        wireTransfers = lotteryContext.getBean()
        wireTransfers.setFunds("123-12312", 100)
    }

    @Test
    fun lottery() {
        administration.resetLottery()
        assertEquals(0, administration.allSubmittedTickets.size)
        assertNotNull(service.submitTicket(LotteryTestUtils.createLotteryTicket("cvt@bbb.com", "123-12312", "+32425255", setOf(1, 2, 3, 4))))
        assertNotNull(service.submitTicket(LotteryTestUtils.createLotteryTicket("ant@bac.com", "123-12312", "+32423455", setOf(11, 12, 13, 14))))
        assertNotNull(service.submitTicket(LotteryTestUtils.createLotteryTicket("arg@boo.com", "123-12312", "+32421255", setOf(6, 8, 13, 19))))
        assertEquals(3, administration.allSubmittedTickets.size)
        val winningNumbers = administration.performLottery()
        assertNotNull(service.submitTicket(LotteryTestUtils.createLotteryTicket("lucky@orb.com", "123-12312", "+12421255", winningNumbers.numbers)))
        assertEquals(4, administration.allSubmittedTickets.size)
        administration.allSubmittedTickets.keys.forEach {
            val checkResult = service.checkTicketForPrize(it, winningNumbers)
            assertNotEquals(LotteryTicketCheckResult.CheckResult.TICKET_NOT_SUBMITTED, checkResult.result)
            if (checkResult.result == LotteryTicketCheckResult.CheckResult.WIN_PRIZE) assertTrue(checkResult.prizeAmount > 0)
            else assertEquals(0, checkResult.prizeAmount)
        }
        val checkResult = service.checkTicketForPrize(LotteryTicketId(), winningNumbers)
        assertEquals(LotteryTicketCheckResult.CheckResult.TICKET_NOT_SUBMITTED, checkResult.result)
        assertEquals(0, checkResult.prizeAmount)
    }
}