import io.github.oshai.kotlinlogging.KotlinLogging

private const val PRODUCT_COST = 50.0
private val logger = KotlinLogging.logger {}
fun main() {
    logger.info { "Foreign Tax applied: ${InvoiceGenerator(PRODUCT_COST, ForeignTaxCalculator()).amountWithTax}" }
    logger.info { "Domestic Tax applied: ${InvoiceGenerator(PRODUCT_COST, DomesticTaxCalculator()).amountWithTax}" }
}
