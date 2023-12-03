import io.github.oshai.kotlinlogging.KotlinLogging

class HolderThreadSafe {
    private val logger = KotlinLogging.logger {}
    val heavy by lazy { Heavy() }

    init {
        logger.info { "HolderThreadSafe created" }
    }
}
