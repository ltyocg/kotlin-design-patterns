package com.ltyocg.abstractdocument

import com.ltyocg.abstractdocument.domain.Car
import com.ltyocg.abstractdocument.domain.enums.Property
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
    log.info("-> model: {}", car.getModel())
    log.info("-> price: {}", car.getPrice())
    log.info("-> parts:")
    car.getParts().forEach { log.info("\t{}/{}/{}", it.getType(), it.getModel(), it.getPrice()) }
}
