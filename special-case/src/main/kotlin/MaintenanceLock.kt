import io.github.oshai.kotlinlogging.KotlinLogging

object MaintenanceLock {
    private val logger = KotlinLogging.logger {}
    var lock = true
        set(lock) {
            field = lock
            logger.info { "Maintenance lock is set to: $lock" }
        }
}
