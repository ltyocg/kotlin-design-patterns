fun interface TaxCalculator : (Double) -> Double

class DomesticTaxCalculator : TaxCalculator {
    override fun invoke(amount: Double): Double = amount * 20.0 / 100.0
}

class ForeignTaxCalculator : TaxCalculator {
    override fun invoke(amount: Double): Double = amount * 60.0 / 100.0
}