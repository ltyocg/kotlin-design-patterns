package api.gateway.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.IOException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

interface PriceClient {
    val price: String?
}

@Component
class PriceClientImpl : PriceClient {
    private val log = LoggerFactory.getLogger(javaClass)
    override val price: String?
        get() {
            try {
                log.info("Sending request to fetch price info")
                return HttpClient.newHttpClient().send(
                    HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create("http://localhost:50006/price"))
                        .build(),
                    HttpResponse.BodyHandlers.ofString()
                ).also {
                    if (it.statusCode() / 100 == 2) log.info("Price info received successfully")
                    else log.warn("Price info request failed")
                }.body()
            } catch (e: IOException) {
                log.error("Failure occurred while getting price info", e)
            } catch (e: InterruptedException) {
                log.error("Failure occurred while getting price info", e)
                Thread.currentThread().interrupt()
            }
            return null
        }
}