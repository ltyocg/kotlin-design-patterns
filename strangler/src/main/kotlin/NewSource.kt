import io.github.oshai.kotlinlogging.KotlinLogging

object NewSource {
    private val logger = KotlinLogging.logger {}
    fun accumulateSum(vararg nums: Int): Int {
        info()
        return nums.sum()
    }

    fun accumulateMul(vararg nums: Int): Int {
        info()
        return nums.fold(1) { acc, i -> acc * i }
    }

    fun ifNonZero(vararg nums: Int): Boolean {
        info()
        return nums.all { it != 0 }
    }

    private fun info() = logger.info { "Source module 2.0" }
}
