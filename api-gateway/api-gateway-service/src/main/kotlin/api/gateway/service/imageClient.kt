package api.gateway.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import java.io.IOException

interface ImageClient {
    val imagePath: String?
}

@Component
class ImageClientImpl(private val restTemplate: RestTemplate) : ImageClient {
    private val log = LoggerFactory.getLogger(javaClass)
    override val imagePath: String?
        get() {
            try {
                log.info("Sending request to fetch image path")
                val responseEntity = restTemplate.getForEntity<String>("http://localhost:50005/image-path")
                if (responseEntity.statusCodeValue / 100 == 2) log.info("Image path received successfully")
                else log.warn("Image path request failed")
                return responseEntity.body
            } catch (e: IOException) {
                log.error("Failure occurred while getting image path", e)
            }
            return null
        }
}