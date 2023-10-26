import org.slf4j.LoggerFactory

class Bank(accountNum: Int, baseAmount: Int) {
    private val log = LoggerFactory.getLogger(javaClass)
    val accounts = IntArray(accountNum) { baseAmount }

    @Synchronized
    fun transfer(accountA: Int, accountB: Int, amount: Int) {
        if (accounts[accountA] >= amount) {
            accounts[accountB] += amount
            accounts[accountA] -= amount
            if (log.isDebugEnabled) log.debug("Transferred from account : {} to account : {} , amount : {} . balance : {}", accountA, accountB, amount, balance)
        }
    }

    val balance: Int
        @Synchronized
        get() = accounts.sum()
}