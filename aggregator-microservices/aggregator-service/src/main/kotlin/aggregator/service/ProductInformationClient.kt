package aggregator.service

import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.IOException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

object ProductInformationClient {
    private val logger = KotlinLogging.logger {}
    fun getProductTitle(): String? {
        try {
            return HttpClient.newHttpClient().send(
                HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://localhost:51515/information"))
                    .build(),
                HttpResponse.BodyHandlers.ofString()
            ).body()
        } catch (e: IOException) {
            logger.error(e) { "IOException Occurred" }
        }
        return null
    }
}
