import org.slf4j.LoggerFactory

object HalfSource {
    private val log = LoggerFactory.getLogger(javaClass)
    private const val VERSION = "1.5"
    fun accumulateSum(vararg nums: Int): Int {
        log.info("Source module {}", VERSION)
        return nums.sum()
    }

    fun ifNonZero(vararg nums: Int): Boolean {
        log.info("Source module {}", VERSION)
        return nums.all { it != 0 }
    }
}