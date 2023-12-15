import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

private const val ACCOUNT_NUM = 4
private const val BASE_AMOUNT = 1000

class BankTest {
    private lateinit var bank: Bank

    @BeforeTest
    fun setup() {
        bank = Bank(ACCOUNT_NUM, BASE_AMOUNT)
    }

    @Test
    fun `length of accounts have to equals to account num constant`() = assertEquals(ACCOUNT_NUM, bank.accounts.size)

    @Test
    fun `transfer method have to transfer amount from an account to other account`() {
        bank.transfer(0, 1, 1000)
        assertEquals(0, bank.accounts[0])
        assertEquals(2000, bank.accounts[1])
    }

    @Test
    fun `balance have to be OK`() = assertEquals(4000, bank.getBalance())

    @Test
    fun `return balance when given account number`() {
        bank.transfer(0, 1, 1000)
        assertEquals(0, bank.getBalance(0))
        assertEquals(2000, bank.getBalance(1))
    }
}
