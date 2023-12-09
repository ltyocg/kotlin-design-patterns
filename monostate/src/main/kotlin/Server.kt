import io.github.oshai.kotlinlogging.KotlinLogging

class Server(val host: String, val port: Int, val id: Int) {
    private val logger = KotlinLogging.logger {}
    fun serve(request: Request) =
        logger.info { "Server ID $id associated to host : $host and port $port. Processed request with value ${request.value}" }
}
