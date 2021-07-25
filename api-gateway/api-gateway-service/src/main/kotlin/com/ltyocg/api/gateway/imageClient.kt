package com.ltyocg.api.gateway

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.IOException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

interface ImageClient {
    val imagePath: String?
}

@Component
class ImageClientImpl : ImageClient {
    private val log = LoggerFactory.getLogger(this::class.java)
    override val imagePath: String?
        get() {
            try {
                log.info("Sending request to fetch image path")
                return HttpClient.newHttpClient().send(
                    HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create("http://localhost:50005/image-path"))
                        .build(),
                    BodyHandlers.ofString()
                ).also {
                    if (it.statusCode() / 100 == 2) log.info("Image path received successfully")
                    else log.warn("Image path request failed")
                }.body()
            } catch (e: IOException) {
                log.error("Failure occurred while getting image path", e)
            } catch (e: InterruptedException) {
                log.error("Failure occurred while getting image path", e)
                Thread.currentThread().interrupt()
            }
            return null
        }
}