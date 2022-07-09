import org.slf4j.LoggerFactory

object NewSource {
    private val log = LoggerFactory.getLogger(javaClass)
    private const val VERSION = "2.0"
    private const val SOURCE_MODULE = "Source module {}"
    fun accumulateSum(vararg nums: Int): Int {
        log.info(SOURCE_MODULE, VERSION)
        return nums.sum()
    }

    fun accumulateMul(vararg nums: Int): Int {
        log.info(SOURCE_MODULE, VERSION)
        return nums.fold(1) { acc, i -> acc * i }
    }

    fun ifNonZero(vararg nums: Int): Boolean {
        log.info(SOURCE_MODULE, VERSION)
        return nums.all { it != 0 }
    }
}