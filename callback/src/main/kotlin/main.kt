import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() = SimpleTask.executeWith { logger.info { "I'm done now." } }
