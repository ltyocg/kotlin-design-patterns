import java.math.BigDecimal
import java.util.*
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class IntegrationTest {
    private lateinit var eventProcessor: DomainEventProcessor

    @BeforeTest
    fun initialize() {
        eventProcessor = DomainEventProcessor(JsonFileJournal())
    }

    @Test
    fun `state recovery`() {
        with(eventProcessor) {
            reset()
            process(AccountCreateEvent(0, Date().time, ACCOUNT_OF_DAENERYS, "Daenerys Targaryen"))
            process(AccountCreateEvent(1, Date().time, ACCOUNT_OF_JON, "Jon Snow"))
            process(MoneyDepositEvent(2, Date().time, ACCOUNT_OF_DAENERYS, BigDecimal("100000")))
            process(MoneyDepositEvent(3, Date().time, ACCOUNT_OF_JON, BigDecimal("100")))
            process(MoneyTransferEvent(4, Date().time, BigDecimal("10000"), ACCOUNT_OF_DAENERYS, ACCOUNT_OF_JON))
        }
        val accountOfDaenerysBeforeShotDown = AccountAggregate.getAccount(ACCOUNT_OF_DAENERYS)!!
        val accountOfJonBeforeShotDown = AccountAggregate.getAccount(ACCOUNT_OF_JON)!!
        AccountAggregate.resetState()
        eventProcessor = DomainEventProcessor(JsonFileJournal())
        with(eventProcessor) {
            recover()
            assertEquals(accountOfDaenerysBeforeShotDown.money, AccountAggregate.getAccount(ACCOUNT_OF_DAENERYS)?.money)
            assertEquals(accountOfJonBeforeShotDown.money, AccountAggregate.getAccount(ACCOUNT_OF_JON)?.money)
        }
    }
}
