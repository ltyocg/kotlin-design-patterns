import org.slf4j.LoggerFactory

class HalfArithmetic(
    private val newSource: HalfSource,
    private val oldSource: OldSource
) {
    private companion object {
        private const val VERSION = "1.5"
    }

    private val log = LoggerFactory.getLogger(javaClass)
    fun sum(vararg nums: Int): Int {
        log.info("Arithmetic sum {}", VERSION)
        return newSource.accumulateSum(*nums)
    }

    fun mul(vararg nums: Int): Int {
        log.info("Arithmetic mul {}", VERSION)
        return oldSource.accumulateMul(*nums)
    }

    fun ifHasZero(vararg nums: Int): Boolean {
        log.info("Arithmetic check zero {}", VERSION)
        return !newSource.ifNonZero(*nums)
    }
}