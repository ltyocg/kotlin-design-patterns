import java.util.logging.Logger

class Bank(accountNum: Int, baseAmount: Int, var logger: Logger) {
    val accounts = IntArray(accountNum) { baseAmount }

    @Synchronized
    fun transfer(accountA: Int, accountB: Int, amount: Int) {
        if (accounts[accountA] >= amount) {
            accounts[accountB] += amount
            accounts[accountA] -= amount
            logger.info("Transferred from account :$accountA to account :$accountB , amount :$amount . balance :$balance")
        }
    }

    val balance: Int
        @Synchronized
        get() = accounts.sum()
}