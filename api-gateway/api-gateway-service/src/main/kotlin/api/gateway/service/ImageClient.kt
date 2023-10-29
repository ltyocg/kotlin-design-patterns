package api.gateway.service

import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.IOException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

object ImageClient {
    private val logger = KotlinLogging.logger {}
    val imagePath: String?
        get() {
            try {
                logger.info { "Sending request to fetch image path" }
                return HttpClient.newHttpClient().send(
                    HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create("http://localhost:50005/image-path"))
                        .build(),
                    HttpResponse.BodyHandlers.ofString()
                ).also {
                    if (it.statusCode() / 100 == 2) logger.info { "Image path received successfully" }
                    else logger.warn { "Image path request failed" }
                }.body()
            } catch (e: IOException) {
                logger.error(e) { "Failure occurred while getting image path" }
            } catch (e: InterruptedException) {
                logger.error(e) { "Failure occurred while getting image path" }
                Thread.currentThread().interrupt()
            }
            return null
        }
}
