import org.slf4j.LoggerFactory

class Bank(accountNum: Int, baseAmount: Int) {
    private val log = LoggerFactory.getLogger(javaClass)
    val accounts = IntArray(accountNum) { baseAmount }

    @Synchronized
    fun transfer(accountA: Int, accountB: Int, amount: Int) {
        if (accounts[accountA] >= amount) {
            accounts[accountB] += amount
            accounts[accountA] -= amount
            log.info("Transferred from account :$accountA to account :$accountB , amount :$amount . balance :$balance")
        }
    }

    val balance: Int
        @Synchronized
        get() = accounts.sum()
}