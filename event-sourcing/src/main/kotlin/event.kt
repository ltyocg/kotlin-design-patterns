import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
abstract class DomainEvent {
    abstract val sequenceId: Long
    abstract val createdTime: Long
    var realTime = true
    abstract fun process()
}

@Serializable
@SerialName("AccountCreateEvent")
class AccountCreateEvent(
    override val sequenceId: Long,
    override val createdTime: Long,
    val accountNo: Int,
    val owner: String
) : DomainEvent() {
    override fun process() {
        if (AccountAggregate.getAccount(accountNo) != null) throw RuntimeException("Account already exists")
        Account(accountNo, owner).handleEvent(this)
    }
}

@Serializable
@SerialName("MoneyDepositEvent")
class MoneyDepositEvent(
    override val sequenceId: Long,
    override val createdTime: Long,
    val accountNo: Int,
    @Contextual val money: BigDecimal
) : DomainEvent() {
    override fun process() = AccountAggregate.getAccount(accountNo)?.handleEvent(this) ?: throw RuntimeException("Account not found")
}

@Serializable
@SerialName("MoneyTransferEvent")
class MoneyTransferEvent(
    override val sequenceId: Long,
    override val createdTime: Long,
    @Contextual val money: BigDecimal,
    val accountNoFrom: Int,
    val accountNoTo: Int
) : DomainEvent() {
    override fun process() {
        AccountAggregate.getAccount(accountNoFrom)?.handleTransferFromEvent(this) ?: throw RuntimeException("Account not found $accountNoFrom")
        AccountAggregate.getAccount(accountNoTo)?.handleTransferToEvent(this) ?: throw RuntimeException("Account not found $accountNoTo")
    }
}
