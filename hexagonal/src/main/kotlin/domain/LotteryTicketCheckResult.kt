package domain

data class LotteryTicketCheckResult(
    val result: CheckResult,
    val prizeAmount: Int = 0
) {
    enum class CheckResult {
        WIN_PRIZE,
        NO_PRIZE,
        TICKET_NOT_SUBMITTED
    }
}