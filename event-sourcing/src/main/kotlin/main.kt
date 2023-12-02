import io.github.oshai.kotlinlogging.KotlinLogging
import java.math.BigDecimal
import java.util.*

const val ACCOUNT_OF_DAENERYS = 1
const val ACCOUNT_OF_JON = 2
private val logger = KotlinLogging.logger {}
fun main() {
    with(DomainEventProcessor()) {
        logger.info { "Running the system first time............" }
        reset()
        logger.info { "Creating the accounts............" }
        process(AccountCreateEvent(0, Date().time, ACCOUNT_OF_DAENERYS, "Daenerys Targaryen"))
        process(AccountCreateEvent(1, Date().time, ACCOUNT_OF_JON, "Jon Snow"))
        logger.info { "Do some money operations............" }
        process(MoneyDepositEvent(2, Date().time, ACCOUNT_OF_DAENERYS, BigDecimal("100000")))
        process(MoneyDepositEvent(3, Date().time, ACCOUNT_OF_JON, BigDecimal("100")))
        process(MoneyTransferEvent(4, Date().time, BigDecimal("10000"), ACCOUNT_OF_DAENERYS, ACCOUNT_OF_JON))
        logger.info { "...............State:............" }
        logger.info { AccountAggregate.getAccount(ACCOUNT_OF_DAENERYS) }
        logger.info { AccountAggregate.getAccount(ACCOUNT_OF_JON) }
        logger.info { "At that point system had a shut down, state in memory is cleared............" }
        AccountAggregate.resetState()
        logger.info { "Recover the system by the events in journal file............" }
    }
    with(DomainEventProcessor()) {
        recover()
        logger.info { "...............Recovered State:............" }
        logger.info { AccountAggregate.getAccount(ACCOUNT_OF_DAENERYS) }
        logger.info { AccountAggregate.getAccount(ACCOUNT_OF_JON) }
    }
}
