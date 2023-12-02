import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.*

fun main() {
    val server = Server("localhost", 8080)
    server.process(Request("Data1", server.getSession("Session1")))
    server.process(Request("Data2", server.getSession("Session2")))
}

data class Server(
    val host: String,
    val port: Int
) {
    private val logger = KotlinLogging.logger {}
    fun getSession(name: String): Session = Session(UUID.randomUUID().toString(), name)
    fun process(request: Request) = logger.info { "Processing Request with client: ${request.session.clientName} data: ${request.data}" }
}

data class Request(
    val data: String,
    val session: Session
)

data class Session(
    val id: String,
    val clientName: String
)
