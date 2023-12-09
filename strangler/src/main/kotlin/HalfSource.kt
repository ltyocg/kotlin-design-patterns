import io.github.oshai.kotlinlogging.KotlinLogging

object HalfSource {
    private val logger = KotlinLogging.logger {}
    fun accumulateSum(vararg nums: Int): Int {
        info()
        return nums.sum()
    }

    fun ifNonZero(vararg nums: Int): Boolean {
        info()
        return nums.all { it != 0 }
    }

    private fun info() = logger.info { "Source module 1.5" }
}
