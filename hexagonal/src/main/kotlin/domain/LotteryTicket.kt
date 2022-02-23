package domain

import java.util.*

class LotteryTicket(
    val id: LotteryTicketId,
    val playerDetails: PlayerDetails,
    val lotteryNumbers: LotteryNumbers
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as LotteryTicket
        return playerDetails == other.playerDetails && lotteryNumbers == other.lotteryNumbers
    }

    override fun hashCode(): Int = Objects.hash(playerDetails, lotteryNumbers)
}
