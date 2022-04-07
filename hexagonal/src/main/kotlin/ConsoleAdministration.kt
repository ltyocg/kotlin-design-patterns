import org.slf4j.LoggerFactory
import org.springframework.beans.factory.getBean
import java.util.*

object ConsoleAdministration {
    private val log = LoggerFactory.getLogger(javaClass)

    @JvmStatic
    fun main(args: Array<String>) {
        MongoConnectionPropertiesLoader.load()
        SampleData.submitTickets(lotteryContext.getBean(), 20)
        val consoleAdministration = ConsoleAdministrationSrvImpl(lotteryContext.getBean(), log)
        Scanner(System.`in`).use {
            var exit = false
            while (!exit) {
                printMainMenu()
                when (val cmd = readString(it)) {
                    "1" -> consoleAdministration.getAllSubmittedTickets()
                    "2" -> consoleAdministration.performLottery()
                    "3" -> consoleAdministration.resetLottery()
                    "4" -> exit = true
                    else -> log.info("Unknown command: {}", cmd)
                }
            }
        }
    }

    private fun printMainMenu() {
        log.info("")
        log.info("### Lottery Administration Console ###")
        log.info("(1) Show all submitted tickets")
        log.info("(2) Perform lottery draw")
        log.info("(3) Reset lottery ticket database")
        log.info("(4) Exit")
    }

    private fun readString(scanner: Scanner): String? {
        log.info("> ")
        return scanner.next()
    }
}