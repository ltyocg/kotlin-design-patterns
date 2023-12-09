import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    SlidingDoor().use { logger.info { "Walking in." } }
    TreasureChest().use { logger.info { "Looting contents." } }
}
