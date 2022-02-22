package aggregator.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.IOException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

interface ProductInformationClient {
    val productTitle: String?
}

@Component
class ProductInformationClientImpl : ProductInformationClient {
    private val log = LoggerFactory.getLogger(javaClass)
    override val productTitle: String?
        get() {
            try {
                return HttpClient.newHttpClient().send(
                    HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create("http://localhost:51515/information"))
                        .build(),
                    HttpResponse.BodyHandlers.ofString()
                ).body()
            } catch (e: IOException) {
                log.error("IOException Occurred", e)
            } catch (e: InterruptedException) {
                log.error("InterruptedException Occurred", e)
                Thread.currentThread().interrupt()
            }
            return null
        }
}