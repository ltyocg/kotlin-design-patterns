package com.ltyocg.event.sourcing

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.math.BigDecimal

sealed class DomainEvent(
    val sequenceId: Long,
    val createdTime: Long,
    val eventClassName: String
) : Serializable {
    var realTime = true
    abstract fun process()
}

class AccountCreateEvent @JsonCreator constructor(
    @JsonProperty("sequenceId") sequenceId: Long,
    @JsonProperty("createdTime") createdTime: Long,
    @JsonProperty("accountNo") val accountNo: Int,
    @JsonProperty("owner") val owner: String
) : DomainEvent(sequenceId, createdTime, AccountCreateEvent::class.simpleName!!) {
    override fun process() {
        if (AccountAggregate.getAccount(accountNo) != null) throw RuntimeException("Account already exists")
        Account(accountNo, owner).handleEvent(this)
    }
}

class MoneyDepositEvent @JsonCreator constructor(
    @JsonProperty("sequenceId") sequenceId: Long,
    @JsonProperty("createdTime") createdTime: Long,
    @JsonProperty("accountNo") val accountNo: Int,
    @JsonProperty("money") val money: BigDecimal
) : DomainEvent(sequenceId, createdTime, MoneyDepositEvent::class.simpleName!!) {
    override fun process() {
        AccountAggregate.getAccount(accountNo)?.handleEvent(this) ?: throw RuntimeException("Account not found")
    }
}

class MoneyTransferEvent @JsonCreator constructor(
    @JsonProperty("sequenceId") sequenceId: Long,
    @JsonProperty("createdTime") createdTime: Long,
    @JsonProperty("money") val money: BigDecimal,
    @JsonProperty("accountNoFrom") val accountNoFrom: Int,
    @JsonProperty("accountNoTo") val accountNoTo: Int
) : DomainEvent(sequenceId, createdTime, MoneyTransferEvent::class.simpleName!!) {
    override fun process() {
        AccountAggregate.getAccount(accountNoFrom)?.handleTransferFromEvent(this) ?: throw RuntimeException("Account not found $accountNoFrom")
        AccountAggregate.getAccount(accountNoTo)?.handleTransferToEvent(this) ?: throw RuntimeException("Account not found $accountNoTo")
    }
}