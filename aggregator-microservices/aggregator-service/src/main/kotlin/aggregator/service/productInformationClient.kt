package aggregator.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.io.IOException

interface ProductInformationClient {
    val productTitle: String?
}

@Component
class ProductInformationClientImpl(
    private val restTemplate: RestTemplate
) : ProductInformationClient {
    private val log = LoggerFactory.getLogger(javaClass)
    override val productTitle: String?
        get() {
            try {
                return restTemplate.getForObject("http://localhost:51515/information")
            } catch (e: IOException) {
                log.error("IOException Occurred", e)
            } catch (e: InterruptedException) {
                log.error("InterruptedException Occurred", e)
                Thread.currentThread().interrupt()
            }
            return null
        }
}