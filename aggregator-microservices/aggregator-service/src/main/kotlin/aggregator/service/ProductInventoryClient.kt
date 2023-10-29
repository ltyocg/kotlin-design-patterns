package aggregator.service

import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.IOException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

object ProductInventoryClient {
    private val logger = KotlinLogging.logger {}
    fun getProductInventories(): Int? {
        var response = ""
        try {
            response = HttpClient.newHttpClient().send(
                HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://localhost:51516/inventories"))
                    .build(),
                HttpResponse.BodyHandlers.ofString()
            ).body()
        } catch (e: IOException) {
            logger.error(e) { "IOException Occurred" }
        }
        return if (response.isEmpty()) null else response.toInt()
    }
}
