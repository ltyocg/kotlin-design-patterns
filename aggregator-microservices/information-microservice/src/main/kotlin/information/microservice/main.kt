package information.microservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class InformationApplication

fun main(args: Array<String>) {
    runApplication<InformationApplication>(*args)
}

@RestController
class InformationController {
    @GetMapping("/information")
    fun getProductTitle(): String = "The Product Title."
}