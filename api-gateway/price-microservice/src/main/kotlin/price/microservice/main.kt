package price.microservice

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class PriceApplication

fun main(args: Array<String>) {
    runApplication<PriceApplication>(*args)
}

@RestController
class PriceController {
    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/price")
    fun getPrice(): String {
        log.info("Successfully found price info")
        return "20"
    }
}