class InvoiceGenerator(
    private val amount: Double,
    private val taxCalculator: TaxCalculator
) {
    val amountWithTax: Double
        get() = amount + taxCalculator(amount)
}