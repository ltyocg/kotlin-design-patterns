import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    with(CompositeEntity) {
        init()
        setData("No Danger", "Green Light")
        getData().forEach { logger.info { it } }
        setData("Danger", "Red Light")
        getData().forEach { logger.info { it } }
    }
}
