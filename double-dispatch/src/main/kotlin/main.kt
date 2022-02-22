import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")

fun main() {
    val objects = listOf(
        FlamingAsteroid(0, 0, 5, 5),
        SpaceStationMir(1, 1, 2, 2),
        Meteoroid(10, 10, 15, 15),
        SpaceStationIss(12, 12, 14, 14)
    )
    objects.forEach { log.info(it.toString()) }
    log.info("")
    objects.forEach { o1 ->
        objects.forEach { o2 ->
            if (o1 !== o2 && o1.intersectsWith(o2)) o1.collision(o2)
        }
    }
    log.info("")
    objects.forEach { log.info(it.toString()) }
    log.info("")
}