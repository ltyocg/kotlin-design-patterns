import org.slf4j.LoggerFactory

object MaintenanceLock {
    private val log = LoggerFactory.getLogger(javaClass)
    var lock = true
        set(lock) {
            field = lock
            log.info("Maintenance lock is set to: {}", lock)
        }
}