import kotlin.test.Test
import kotlin.test.assertEquals

class InvestorRoleTest {
    @Test
    fun investTest() = assertEquals("Investor test has invested 10 dollars", InvestorRole().apply {
        name = "test"
        amountToInvest = 10
    }.invest())
}