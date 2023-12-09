import io.github.oshai.kotlinlogging.KotlinLogging

class OldArithmetic(private val source: OldSource) {
    private companion object {
        private const val VERSION = "1.0"
    }

    private val logger = KotlinLogging.logger {}
    fun sum(vararg nums: Int): Int {
        logger.info { "Arithmetic sum $VERSION" }
        return source.accumulateSum(*nums)
    }

    fun mul(vararg nums: Int): Int {
        logger.info { "Arithmetic mul $VERSION" }
        return source.accumulateMul(*nums)
    }
}
