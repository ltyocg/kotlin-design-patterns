import io.github.oshai.kotlinlogging.KotlinLogging

object OldSource {
    private val logger = KotlinLogging.logger {}
    fun accumulateSum(vararg nums: Int): Int {
        info()
        return nums.sum()
    }

    fun accumulateMul(vararg nums: Int): Int {
        info()
        var sum = 1
        for (num in nums) sum *= num
        return sum
    }

    private fun info() = logger.info { "Source module 1.0" }
}
