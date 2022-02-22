import domain.Car
import domain.enums.Property
import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")

fun main() {
    log.info("Constructing parts and car")
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
    log.info("Here is our car:")
    log.info("-> model: {}", car.model)
    log.info("-> price: {}", car.price)
    log.info("-> parts:")
    car.parts.forEach { log.info("\t{}/{}/{}", it.type, it.model, it.price) }
}
