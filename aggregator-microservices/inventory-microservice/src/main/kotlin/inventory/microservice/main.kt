package inventory.microservice

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, 51516) {
        routing {
            get("/inventories") {
                call.respondText("5")
            }
        }
    }.start(true)
}
