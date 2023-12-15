import io.github.oshai.kotlinlogging.KotlinLogging

class Bank(accountNum: Int, baseAmount: Int) {
    private val logger = KotlinLogging.logger {}
    val accounts = IntArray(accountNum) { baseAmount }

    @Synchronized
    fun transfer(accountA: Int, accountB: Int, amount: Int) {
        if (accounts[accountA] >= amount) {
            accounts[accountB] += amount
            accounts[accountA] -= amount
            logger.debug {
                "Transferred from account : $accountA to account : $accountB , amount : $amount . bank balance at : ${
                    getBalance()
                }, source account balance: ${
                    getBalance(accountA)
                }, destination account balance: ${
                    getBalance(accountB)
                }"
            }
        }
    }

    @Synchronized
    fun getBalance(accountNumber: Int? = null): Int =
        if (accountNumber == null) accounts.sum()
        else accounts[accountNumber]
}
