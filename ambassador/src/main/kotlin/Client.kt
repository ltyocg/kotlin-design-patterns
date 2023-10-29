import io.github.oshai.kotlinlogging.KotlinLogging

class Client {
    private val logger = KotlinLogging.logger {}
    suspend fun useService(value: Int): Long =
        ServiceAmbassador.doRemoteFunction(value).also { logger.info { "Service result: $it" } }
}
