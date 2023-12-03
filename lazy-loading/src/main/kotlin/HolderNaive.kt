import io.github.oshai.kotlinlogging.KotlinLogging

class HolderNaive {
    private val logger = KotlinLogging.logger {}
    val heavy by lazy(LazyThreadSafetyMode.NONE) { Heavy() }

    init {
        logger.info { "HolderNaive created" }
    }
}
