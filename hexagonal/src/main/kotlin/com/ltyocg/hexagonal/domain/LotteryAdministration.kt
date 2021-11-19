package com.ltyocg.hexagonal.domain

import com.ltyocg.hexagonal.LotteryEventLog
import com.ltyocg.hexagonal.LotteryTicketRepository
import com.ltyocg.hexagonal.WireTransfers
import javax.inject.Inject

class LotteryAdministration
@Inject constructor(
    private val repository: LotteryTicketRepository,
    private val notifications: LotteryEventLog,
    private val wireTransfers: WireTransfers
) {
    val allSubmittedTickets: Map<LotteryTicketId, LotteryTicket>
        get() = repository.findAll()

    fun performLottery(): LotteryNumbers {
        val numbers = LotteryNumbers.createRandom()
        allSubmittedTickets.forEach { (id, lotteryTicket) ->
            val playerDetails = lotteryTicket.playerDetails
            @Suppress("NON_EXHAUSTIVE_WHEN")
            when (LotteryUtils.checkTicketForPrize(repository, id, numbers).result) {
                LotteryTicketCheckResult.CheckResult.WIN_PRIZE ->
                    if (wireTransfers.transferFunds(LotteryConstants.PRIZE_AMOUNT, LotteryConstants.SERVICE_BANK_ACCOUNT, playerDetails.bankAccount))
                        notifications.ticketWon(playerDetails, LotteryConstants.PRIZE_AMOUNT)
                    else notifications.prizeError(playerDetails, LotteryConstants.PRIZE_AMOUNT)
                LotteryTicketCheckResult.CheckResult.NO_PRIZE -> notifications.ticketDidNotWin(playerDetails)
                else -> {}
            }
        }
        return numbers
    }

    fun resetLottery() {
        repository.deleteAll()
    }
}