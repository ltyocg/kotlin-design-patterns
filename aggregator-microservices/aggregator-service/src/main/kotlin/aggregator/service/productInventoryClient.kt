package aggregator.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.io.IOException

interface ProductInventoryClient {
    val productInventories: Int?
}

@Component
class ProductInventoryClientImpl(
    private val restTemplate: RestTemplate
) : ProductInventoryClient {
    private val log = LoggerFactory.getLogger(javaClass)
    override val productInventories: Int?
        get() {
            var response = ""
            try {
                response = restTemplate.getForObject("http://localhost:51516/inventories")
            } catch (e: IOException) {
                log.error("IOException Occurred", e)
            } catch (e: InterruptedException) {
                log.error("InterruptedException Occurred", e)
                Thread.currentThread().interrupt()
            }
            return if (response.isEmpty()) null else response.toInt()
        }
}