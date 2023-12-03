import io.github.oshai.kotlinlogging.KotlinLogging

@Permission
class Guard {
    private val logger = KotlinLogging.logger {}
    fun enter() = logger.info { "You can enter" }
}
