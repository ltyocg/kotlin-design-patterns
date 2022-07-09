import org.slf4j.LoggerFactory

class NewArithmetic(private val source: NewSource) {
    companion object {
        private const val VERSION = "2.0"
    }

    private val log = LoggerFactory.getLogger(javaClass)
    fun sum(vararg nums: Int): Int {
        log.info("Arithmetic sum {}", VERSION)
        return source.accumulateSum(*nums)
    }

    fun mul(vararg nums: Int): Int {
        log.info("Arithmetic mul {}", VERSION)
        return source.accumulateMul(*nums)
    }

    fun ifHasZero(vararg nums: Int): Boolean {
        log.info("Arithmetic check zero {}", VERSION)
        return !source.ifNonZero(*nums)
    }
}