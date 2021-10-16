package com.ltyocg.hexagonal.domain

import com.ltyocg.hexagonal.LotteryEventLog
import com.ltyocg.hexagonal.LotteryTicketRepository
import com.ltyocg.hexagonal.WireTransfers
import javax.inject.Inject

class LotteryService
@Inject constructor(
    private val repository: LotteryTicketRepository,
    private val notifications: LotteryEventLog,
    private val wireTransfers: WireTransfers
) {
    fun submitTicket(ticket: LotteryTicket): LotteryTicketId? {
        val playerDetails = ticket.playerDetails
        return if (wireTransfers.transferFunds(LotteryConstants.TICKET_PRIZE, playerDetails.bankAccount, LotteryConstants.SERVICE_BANK_ACCOUNT)) {
            notifications.ticketSubmitted(playerDetails)
            repository.save(ticket)
        } else {
            notifications.ticketSubmitError(playerDetails)
            null
        }
    }

    fun checkTicketForPrize(id: LotteryTicketId, winningNumbers: LotteryNumbers): LotteryTicketCheckResult =
        LotteryUtils.checkTicketForPrize(repository, id, winningNumbers)
}