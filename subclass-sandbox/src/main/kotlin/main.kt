import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    log.info("Use superpower: sky launch")
    SkyLaunch().activate()
    log.info("Use superpower: ground dive")
    GroundDive().activate()
}