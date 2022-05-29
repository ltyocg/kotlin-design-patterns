package information.microservice

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, 51515, "0.0.0.0") {
        routing {
            get("/information") {
                call.respondText("The Product Title.")
            }
        }
    }.start(true)
}