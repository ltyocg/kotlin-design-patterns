import org.slf4j.LoggerFactory

private const val PRODUCT_COST = 50.0
private val log = LoggerFactory.getLogger("main")
fun main() {
    log.info("Foreign Tax applied: {}", InvoiceGenerator(PRODUCT_COST, ForeignTaxCalculator()).amountWithTax)
    log.info("Domestic Tax applied: {}", InvoiceGenerator(PRODUCT_COST, DomesticTaxCalculator()).amountWithTax)
}