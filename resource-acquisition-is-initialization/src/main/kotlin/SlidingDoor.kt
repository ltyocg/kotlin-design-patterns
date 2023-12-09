import io.github.oshai.kotlinlogging.KotlinLogging

class SlidingDoor : AutoCloseable {
    private val logger = KotlinLogging.logger {}

    init {
        logger.info { "Sliding door opens." }
    }

    override fun close() = logger.info { "Sliding door closes." }
}
