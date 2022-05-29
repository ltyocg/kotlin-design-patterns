package price.microservice

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory

fun main() {
    val log = LoggerFactory.getLogger("main")
    embeddedServer(Netty, 50006, "0.0.0.0") {
        routing {
            get("/price") {
                log.info("Successfully found price info")
                call.respondText("20")
            }
        }
    }
}