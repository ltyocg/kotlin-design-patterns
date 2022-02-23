import org.slf4j.LoggerFactory

class HolderThreadSafe {
    private val log = LoggerFactory.getLogger(javaClass)
    val heavy by lazy { Heavy() }

    init {
        log.info("HolderThreadSafe created")
    }
}