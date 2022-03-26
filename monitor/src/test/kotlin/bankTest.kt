import java.util.logging.Logger
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

private const val ACCOUNT_NUM = 4
private const val BASE_AMOUNT = 1000
private val logger = Logger.getLogger("monitor")

class BankTest {
    private lateinit var bank: Bank

    @BeforeTest
    fun setup() {
        bank = Bank(ACCOUNT_NUM, BASE_AMOUNT, logger)
    }

    @Test
    fun `length of accounts have to equals to account num constant`() = assertEquals(ACCOUNT_NUM, bank.accounts.size)

    @Test
    fun `transfer method have to transfer amount from an account to other account`() {
        bank.transfer(0, 1, 1000)
        assertEquals(0, bank.accounts[0])
        assertEquals(2000, 2000)
    }

    @Test
    fun `balance have to be OK`() = assertEquals(4000, bank.balance)
}