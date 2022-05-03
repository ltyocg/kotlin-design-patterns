import kotlin.test.Test
import kotlin.test.assertEquals

class TaxCalculatorTest {
    @Test
    fun domestic() = assertEquals(DomesticTaxCalculator()(100.0), 20.0)

    @Test
    fun foreign() = assertEquals(ForeignTaxCalculator()(100.0), 60.0)
}