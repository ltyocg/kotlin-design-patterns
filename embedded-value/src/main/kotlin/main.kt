import io.github.oshai.kotlinlogging.KotlinLogging
import kotlin.system.exitProcess

private val logger = KotlinLogging.logger {}
fun main() {
    if (DataSource.createSchema()) {
        logger.info { "TABLE CREATED" }
        logger.info {
            """
            |Table "Orders" schema:
            |${DataSource.getSchema()}
            """.trimMargin()
        }
    } else {
        logger.error { "Error creating table" }
        exitProcess(0)
    }
    logger.info { "Orders Query: ${DataSource.queryOrders().toList()}" }
    DataSource.insertOrder(
        Order(
            item = "JBL headphone",
            orderedBy = "Ram",
            shippingAddress = ShippingAddress("Bangalore", "Karnataka", "560040")
        )
    )
    DataSource.insertOrder(
        Order(
            item = "MacBook Pro",
            orderedBy = "Manjunath",
            shippingAddress = ShippingAddress("Bangalore", "Karnataka", "581204")
        )
    )
    DataSource.insertOrder(
        Order(
            item = "Carrie Soto is Back",
            orderedBy = "Shiva",
            shippingAddress = ShippingAddress("Bangalore", "Karnataka", "560004")
        )
    )
    logger.info { "Orders Query: {DataSource.queryOrders().toList()}\n" }
    logger.info { "Query Order with id=2: ${DataSource.queryOrder(2)}" }
    logger.info { "Remove Order with id=1" }
    DataSource.removeOrder(1)
    logger.info { "\nOrders Query: ${DataSource.queryOrders().toList()}\n" }
    if (DataSource.deleteSchema()) logger.info { "TABLE DROPPED" }
    else logger.error { "Error deleting table" }
}