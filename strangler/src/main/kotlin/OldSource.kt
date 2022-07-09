import org.slf4j.LoggerFactory

object OldSource {
    private val log = LoggerFactory.getLogger(javaClass)
    private const val VERSION = "1.0"
    fun accumulateSum(vararg nums: Int): Int {
        log.info("Source module {}", VERSION)
        var sum = 0
        for (num in nums) sum += num
        return sum
    }

    fun accumulateMul(vararg nums: Int): Int {
        log.info("Source module {}", VERSION)
        var sum = 1
        for (num in nums) sum *= num
        return sum
    }
}