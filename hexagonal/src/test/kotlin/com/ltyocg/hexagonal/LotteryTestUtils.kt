package com.ltyocg.hexagonal

import com.ltyocg.hexagonal.domain.LotteryNumbers
import com.ltyocg.hexagonal.domain.LotteryTicket
import com.ltyocg.hexagonal.domain.LotteryTicketId
import com.ltyocg.hexagonal.domain.PlayerDetails

object LotteryTestUtils {
    fun createLotteryTicket(): LotteryTicket =
        createLotteryTicket("foo@bar.com", "12231-213132", "+99324554", setOf(1, 2, 3, 4))

    fun createLotteryTicket(email: String, account: String, phone: String, givenNumbers: Set<Int>): LotteryTicket =
        LotteryTicket(LotteryTicketId(), PlayerDetails(email, account, phone), LotteryNumbers.create(givenNumbers))
}