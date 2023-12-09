import io.github.oshai.kotlinlogging.KotlinLogging

class HalfArithmetic(
    private val newSource: HalfSource,
    private val oldSource: OldSource
) {
    private companion object {
        private const val VERSION = "1.5"
    }

    private val logger = KotlinLogging.logger {}
    fun sum(vararg nums: Int): Int {
        logger.info { "Arithmetic sum $VERSION" }
        return newSource.accumulateSum(*nums)
    }

    fun mul(vararg nums: Int): Int {
        logger.info { "Arithmetic mul $VERSION" }
        return oldSource.accumulateMul(*nums)
    }

    fun ifHasZero(vararg nums: Int): Boolean {
        logger.info { "Arithmetic check zero $VERSION" }
        return !newSource.ifNonZero(*nums)
    }
}
