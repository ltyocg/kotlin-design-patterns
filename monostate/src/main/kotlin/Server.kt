import org.slf4j.LoggerFactory

class Server(val host: String, val port: Int, val id: Int) {
    private val log = LoggerFactory.getLogger(javaClass)
    fun serve(request: Request) =
        log.info("Server ID {} associated to host : {} and port {}. Processed request with value {}", id, host, port, request.value)
}