import io.github.oshai.kotlinlogging.KotlinLogging

class Bank(accountNum: Int, baseAmount: Int) {
    private val logger = KotlinLogging.logger {}
    val accounts = IntArray(accountNum) { baseAmount }

    @Synchronized
    fun transfer(accountA: Int, accountB: Int, amount: Int) {
        if (accounts[accountA] >= amount) {
            accounts[accountB] += amount
            accounts[accountA] -= amount
            logger.debug { "Transferred from account : $accountA to account : $accountB , amount : $amount . balance : $balance" }
        }
    }

    val balance: Int
        @Synchronized
        get() = accounts.sum()
}
