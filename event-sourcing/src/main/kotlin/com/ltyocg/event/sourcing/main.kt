package com.ltyocg.event.sourcing

import org.slf4j.LoggerFactory
import java.math.BigDecimal
import java.util.*

const val ACCOUNT_OF_DAENERYS = 1
const val ACCOUNT_OF_JON = 2
private val log = LoggerFactory.getLogger("main")

fun main() {
    with(DomainEventProcessor()) {
        log.info("Running the system first time............")
        reset()
        log.info("Creating the accounts............")
        process(AccountCreateEvent(0, Date().time, ACCOUNT_OF_DAENERYS, "Daenerys Targaryen"))
        process(AccountCreateEvent(1, Date().time, ACCOUNT_OF_JON, "Jon Snow"))
        log.info("Do some money operations............")
        process(MoneyDepositEvent(2, Date().time, ACCOUNT_OF_DAENERYS, BigDecimal("100000")))
        process(MoneyDepositEvent(3, Date().time, ACCOUNT_OF_JON, BigDecimal("100")))
        process(MoneyTransferEvent(4, Date().time, BigDecimal("10000"), ACCOUNT_OF_DAENERYS, ACCOUNT_OF_JON))
        log.info("...............State:............")
        log.info(AccountAggregate.getAccount(ACCOUNT_OF_DAENERYS).toString())
        log.info(AccountAggregate.getAccount(ACCOUNT_OF_JON).toString())
        log.info("At that point system had a shut down, state in memory is cleared............")
        AccountAggregate.resetState()
        log.info("Recover the system by the events in journal file............")
    }
    with(DomainEventProcessor()) {
        recover()
        log.info("...............Recovered State:............")
        log.info(AccountAggregate.getAccount(ACCOUNT_OF_DAENERYS).toString())
        log.info(AccountAggregate.getAccount(ACCOUNT_OF_JON).toString())
    }
}