import org.slf4j.LoggerFactory

class OldArithmetic(private val source: OldSource) {
    companion object {
        private const val VERSION = "1.0"
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
}