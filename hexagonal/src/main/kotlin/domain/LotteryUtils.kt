package domain

import LotteryTicketRepository

object LotteryUtils {
    fun checkTicketForPrize(
        repository: LotteryTicketRepository,
        id: LotteryTicketId,
        winningNumbers: LotteryNumbers
    ): LotteryTicketCheckResult {
        val lotteryTicket = repository.findById(id)
        return when {
            lotteryTicket == null -> LotteryTicketCheckResult(LotteryTicketCheckResult.CheckResult.TICKET_NOT_SUBMITTED)
            lotteryTicket.lotteryNumbers == winningNumbers -> LotteryTicketCheckResult(LotteryTicketCheckResult.CheckResult.WIN_PRIZE, 1000)
            else -> LotteryTicketCheckResult(LotteryTicketCheckResult.CheckResult.NO_PRIZE)
        }
    }
}