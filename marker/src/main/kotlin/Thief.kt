import org.slf4j.LoggerFactory

class Thief {
    private val log = LoggerFactory.getLogger(javaClass)
    internal fun steal() = log.info("Steal valuable items")
    internal fun doNothing() = log.info("Pretend nothing happened and just leave")
}