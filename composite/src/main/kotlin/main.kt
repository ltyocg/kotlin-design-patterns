import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}

fun main() {
    with(Messenger) {
        logger.info { "Message from the orcs: " }
        messageFromOrcs().print()
        logger.info { "Message from the elves: " }
        messageFromElves().print()
    }
}
