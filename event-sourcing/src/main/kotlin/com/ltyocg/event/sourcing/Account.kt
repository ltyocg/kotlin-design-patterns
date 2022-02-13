package com.ltyocg.event.sourcing

import org.slf4j.LoggerFactory
import java.math.BigDecimal

data class Account(
    val accountNo: Int,
    val owner: String,
    var money: BigDecimal = BigDecimal.ZERO
) {
    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        private const val MSG = "Some external api for only realtime execution could be called here."
    }

    private fun handleDeposit(money: BigDecimal, realTime: Boolean) {
        this.money += money
        AccountAggregate.putAccount(this)
        if (realTime) log.info(MSG)
    }

    fun handleEvent(moneyDepositEvent: MoneyDepositEvent) {
        handleDeposit(moneyDepositEvent.money, moneyDepositEvent.realTime)
    }

    fun handleEvent(accountCreateEvent: AccountCreateEvent) {
        AccountAggregate.putAccount(this)
        if (accountCreateEvent.realTime) log.info(MSG)
    }

    fun handleTransferFromEvent(moneyTransferEvent: MoneyTransferEvent) {
        if (money < BigDecimal.ZERO) throw RuntimeException("Insufficient Account Balance")
        this.money -= moneyTransferEvent.money
        AccountAggregate.putAccount(this)
        if (moneyTransferEvent.realTime) log.info(MSG)
    }

    fun handleTransferToEvent(moneyTransferEvent: MoneyTransferEvent) {
        handleDeposit(moneyTransferEvent.money, moneyTransferEvent.realTime)
    }
}
