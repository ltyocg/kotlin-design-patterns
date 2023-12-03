package aggregator.service

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, 50004) {
        install(ContentNegotiation) {
            json()
        }
        routing {
            get("/product") {
                call.respond(
                    Product(
                        ProductInformationClient.getProductTitle() ?: "Error: Fetching Product Title Failed",
                        ProductInventoryClient.getProductInventories() ?: -1
                    )
                )
            }
        }
    }.start(true)
}
