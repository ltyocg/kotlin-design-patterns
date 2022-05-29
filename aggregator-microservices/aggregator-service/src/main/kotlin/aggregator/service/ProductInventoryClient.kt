package aggregator.service

import org.slf4j.LoggerFactory
import java.io.IOException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

object ProductInventoryClient {
    private val log = LoggerFactory.getLogger(javaClass)
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
            log.error("IOException Occurred", e)
        }
        return if (response.isEmpty()) null else response.toInt()
    }
}