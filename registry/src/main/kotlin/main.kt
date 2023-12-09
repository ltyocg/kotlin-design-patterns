import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    CustomerRegistry.addCustomer(Customer("1", "John"))
    CustomerRegistry.addCustomer(Customer("2", "Julia"))
    logger.info { "John ${CustomerRegistry.getCustomer("1")}" }
    logger.info { "Julia ${CustomerRegistry.getCustomer("2")}" }
}
