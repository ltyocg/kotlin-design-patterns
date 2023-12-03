import io.github.oshai.kotlinlogging.KotlinLogging

class Thief {
    private val logger = KotlinLogging.logger {}
    internal fun steal() = logger.info { "Steal valuable items" }
    internal fun doNothing() = logger.info { "Pretend nothing happened and just leave" }
}
