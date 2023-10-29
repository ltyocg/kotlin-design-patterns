package price.microservice

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val logger = KotlinLogging.logger {}
fun main() {
    embeddedServer(Netty, 50006, "0.0.0.0") {
        routing {
            get("/price") {
                logger.info { "Successfully found price info" }
                call.respondText("20")
            }
        }
    }
}
