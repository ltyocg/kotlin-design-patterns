import domain.LotteryAdministration
import io.github.oshai.kotlinlogging.KLogger

interface ConsoleAdministrationSrv {
    fun getAllSubmittedTickets()
    fun performLottery()
    fun resetLottery()
}

class ConsoleAdministrationSrvImpl(
    private val administration: LotteryAdministration,
    private val logger: KLogger
) : ConsoleAdministrationSrv {
    override fun getAllSubmittedTickets() = administration.allSubmittedTickets.forEach { (k, v) -> logger.info { "Key: $k, Value: $v" } }
    override fun performLottery() {
        logger.info { "The winning numbers: ${administration.performLottery().numbersAsString}" }
        logger.info { "Time to reset the database for next round, eh?" }
    }

    override fun resetLottery() {
        administration.resetLottery()
        logger.info { "The lottery ticket database was cleared." }
    }
}
