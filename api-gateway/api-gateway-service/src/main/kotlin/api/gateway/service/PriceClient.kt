package api.gateway.service

import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.IOException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

object PriceClient {
    private val logger = KotlinLogging.logger {}
    val price: String?
        get() {
            try {
                logger.info { "Sending request to fetch price info" }
                return HttpClient.newHttpClient().send(
                    HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create("http://localhost:50006/price"))
                        .build(),
                    HttpResponse.BodyHandlers.ofString()
                ).also {
                    if (it.statusCode() / 100 == 2) logger.info { "Price info received successfully" }
                    else logger.warn { "Price info request failed" }
                }.body()
            } catch (e: IOException) {
                logger.error(e) { "Failure occurred while getting price info" }
            } catch (e: InterruptedException) {
                logger.error(e) { "Failure occurred while getting price info" }
                Thread.currentThread().interrupt()
            }
            return null
        }
}
