import io.github.oshai.kotlinlogging.KotlinLogging

class Heavy {
    private val logger = KotlinLogging.logger {}

    init {
        logger.info { "Creating Heavy ..." }
        Thread.sleep(1000)
        logger.info { "... Heavy created" }
    }
}
