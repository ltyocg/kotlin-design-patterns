import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    logger.info { "Constructing parts and car" }
    val car = Car(
        mapOf(
            Property.MODEL.name to "300SL",
            Property.PRICE.name to 10000L,
            Property.PARTS.name to listOf(
                mapOf(
                    Property.TYPE.name to "wheel",
                    Property.MODEL.name to "15C",
                    Property.PRICE.name to 100L
                ),
                mapOf(
                    Property.TYPE.name to "door",
                    Property.MODEL.name to "Lambo",
                    Property.PRICE.name to 300L
                )
            )
        )
    )
    logger.info { "Here is our car:" }
    logger.info { "-> model: ${car.model}" }
    logger.info { "-> price: ${car.price}" }
    logger.info { "-> parts:" }
    car.parts.forEach { logger.info { "\t${it.type}/${it.model}/${it.price}" } }
}
