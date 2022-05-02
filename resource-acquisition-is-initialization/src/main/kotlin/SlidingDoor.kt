import org.slf4j.LoggerFactory

class SlidingDoor : AutoCloseable {
    private val log = LoggerFactory.getLogger(javaClass)

    init {
        log.info("Sliding door opens.")
    }

    override fun close() = log.info("Sliding door closes.")
}