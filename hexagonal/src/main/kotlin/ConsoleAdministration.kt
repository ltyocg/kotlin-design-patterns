import domain.LotteryAdministration
import domain.LotteryService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.getBean
import org.springframework.context.support.StaticApplicationContext
import org.springframework.context.support.beans
import java.util.*

object ConsoleAdministration {
    private val logger = KotlinLogging.logger {}
    private val lotteryContext = StaticApplicationContext().apply {
        beans {
            bean<MongoTicketRepository>()
            bean<MongoEventLog>()
            bean<MongoBank>()
            bean<LotteryService>()
            bean<LotteryAdministration>()
        }.initialize(this)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        MongoConnectionPropertiesLoader.load()
        SampleData.submitTickets(lotteryContext.getBean(), 20)
        val consoleAdministration = ConsoleAdministrationSrvImpl(lotteryContext.getBean(), logger)
        Scanner(System.`in`).use {
            var exit = false
            while (!exit) {
                printMainMenu()
                when (val cmd = readString(it)) {
                    "1" -> consoleAdministration.getAllSubmittedTickets()
                    "2" -> consoleAdministration.performLottery()
                    "3" -> consoleAdministration.resetLottery()
                    "4" -> exit = true
                    else -> logger.info { "Unknown command: $cmd" }
                }
            }
        }
    }

    private fun printMainMenu() {
        logger.info {}
        logger.info { "### Lottery Administration Console ###" }
        logger.info { "(1) Show all submitted tickets" }
        logger.info { "(2) Perform lottery draw" }
        logger.info { "(3) Reset lottery ticket database" }
        logger.info { "(4) Exit" }
    }

    private fun readString(scanner: Scanner): String? {
        logger.info { "> " }
        return scanner.next()
    }
}
