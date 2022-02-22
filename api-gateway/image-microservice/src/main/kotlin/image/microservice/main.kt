package image.microservice

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class ImageApplication

fun main(args: Array<String>) {
    runApplication<ImageApplication>(*args)
}

@RestController
class ImageController {
    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/image-path")
    fun getImagePath(): String {
        log.info("Successfully found image path")
        return "/product-image.png"
    }
}