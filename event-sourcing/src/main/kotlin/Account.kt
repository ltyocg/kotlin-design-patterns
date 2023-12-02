import io.github.oshai.kotlinlogging.KotlinLogging
import java.math.BigDecimal

data class Account(
    val accountNo: Int,
    val owner: String,
    var money: BigDecimal = BigDecimal.ZERO
) {
    private val logger = KotlinLogging.logger {}
    private fun handleDeposit(money: BigDecimal, realTime: Boolean) {
        this.money += money
        AccountAggregate.putAccount(this)
        if (realTime) log()
    }

    fun handleEvent(moneyDepositEvent: MoneyDepositEvent) = handleDeposit(moneyDepositEvent.money, moneyDepositEvent.realTime)
    fun handleEvent(accountCreateEvent: AccountCreateEvent) {
        AccountAggregate.putAccount(this)
        if (accountCreateEvent.realTime) log()
    }

    fun handleTransferFromEvent(moneyTransferEvent: MoneyTransferEvent) {
        if (money < BigDecimal.ZERO) throw RuntimeException("Insufficient Account Balance")
        money -= moneyTransferEvent.money
        AccountAggregate.putAccount(this)
        if (moneyTransferEvent.realTime) log()
    }

    fun handleTransferToEvent(moneyTransferEvent: MoneyTransferEvent) = handleDeposit(moneyTransferEvent.money, moneyTransferEvent.realTime)
    private fun log() = logger.info { "Some external api for only realtime execution could be called here." }
}
