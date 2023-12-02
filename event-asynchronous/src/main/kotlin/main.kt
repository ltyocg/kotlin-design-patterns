import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    with(EventManager()) {
        val asyncEventId = createAsync(60)
        logger.info { "Async Event [$asyncEventId] has been created." }
        start(asyncEventId)
        logger.info { "Async Event [$asyncEventId] has been started." }
        val syncEventId = create(60)
        logger.info { "Sync Event [$syncEventId] has been created." }
        start(syncEventId)
        logger.info { "Sync Event [$syncEventId] has been started." }
        status(asyncEventId)
        status(syncEventId)
        cancel(asyncEventId)
        logger.info { "Async Event [$asyncEventId] has been stopped." }
        cancel(syncEventId)
        logger.info { "Sync Event [$syncEventId] has been stopped." }
    }
}
