package api.gateway.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import java.io.IOException

interface PriceClient {
    val price: String?
}

@Component
class PriceClientImpl(private val restTemplate: RestTemplate) : PriceClient {
    private val log = LoggerFactory.getLogger(javaClass)
    override val price: String?
        get() {
            try {
                log.info("Sending request to fetch price info")
                val responseEntity = restTemplate.getForEntity<String>("http://localhost:50006/price")
                if (responseEntity.statusCodeValue / 100 == 2) log.info("Price info received successfully")
                else log.warn("Price info request failed")
                return responseEntity.body
            } catch (e: IOException) {
                log.error("Failure occurred while getting price info", e)
            }
            return null
        }
}