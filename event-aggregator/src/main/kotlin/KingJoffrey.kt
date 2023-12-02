import io.github.oshai.kotlinlogging.KotlinLogging

class KingJoffrey : EventObserver {
    private val logger = KotlinLogging.logger {}
    override fun onEvent(e: Event?) = logger.info { "Received event from the King's Hand: $e" }
}
