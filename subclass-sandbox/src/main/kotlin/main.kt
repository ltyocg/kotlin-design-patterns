import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    logger.info { "Use superpower: sky launch" }
    SkyLaunch().activate()
    logger.info { "Use superpower: ground dive" }
    GroundDive().activate()
}
