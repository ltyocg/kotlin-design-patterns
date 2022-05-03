import org.mockito.kotlin.*
import kotlin.test.Test
import kotlin.test.assertEquals

class InvoiceGeneratorTest {
    @Test
    fun `generate tax`() {
        val productCost = 50.0
        val tax = 10.0
        val taxCalculatorMock = mock<TaxCalculator>()
        doReturn(tax).whenever(taxCalculatorMock)(productCost)
        assertEquals(InvoiceGenerator(productCost, taxCalculatorMock).amountWithTax, productCost + tax)
        verify(taxCalculatorMock, times(1))(productCost)
    }
}