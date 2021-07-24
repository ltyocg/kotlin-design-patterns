package com.ltyocg.aggregator.microservices

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.IOException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

interface ProductInventoryClient {
    val productInventories: Int?
}

@Component
class ProductInventoryClientImpl : ProductInventoryClient {
    private val log = LoggerFactory.getLogger(ProductInformationClientImpl::class.java)
    override val productInventories: Int?
        get() {
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
            } catch (e: InterruptedException) {
                log.error("InterruptedException Occurred", e)
                Thread.currentThread().interrupt()
            }
            return if (response.isEmpty()) null else response.toInt()
        }
}