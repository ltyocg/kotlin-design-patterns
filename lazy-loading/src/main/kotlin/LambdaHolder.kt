import io.github.oshai.kotlinlogging.KotlinLogging

class LambdaHolder {
    private val logger = KotlinLogging.logger {}
    private var supplier: () -> Heavy = {
        val heavyInstance = Heavy()
        supplier = { heavyInstance }
        supplier()
    }
    val heavy: Heavy
        get() = supplier()

    init {
        logger.info { "Java8Holder created" }
    }
}
