import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    val objects = listOf(
        FlamingAsteroid(0, 0, 5, 5),
        SpaceStationMir(1, 1, 2, 2),
        Meteoroid(10, 10, 15, 15),
        SpaceStationIss(12, 12, 14, 14)
    )
    objects.forEach { logger.info { it } }
    logger.info { "" }
    objects.forEach { o1 ->
        objects.forEach { o2 ->
            if (o1 !== o2 && o1.intersectsWith(o2)) o1.collision(o2)
        }
    }
    logger.info { "" }
    objects.forEach { logger.info { it } }
    logger.info { "" }
}
