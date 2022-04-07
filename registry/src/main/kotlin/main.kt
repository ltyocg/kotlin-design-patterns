import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    CustomerRegistry.addCustomer(Customer("1", "John"))
    CustomerRegistry.addCustomer(Customer("2", "Julia"))
    log.info("John {}", CustomerRegistry.getCustomer("1"))
    log.info("Julia {}", CustomerRegistry.getCustomer("2"))
}