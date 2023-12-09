package repository

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.getBean
import org.springframework.context.annotation.AnnotationConfigApplicationContext

private val logger = KotlinLogging.logger {}
fun main() {
    val context = AnnotationConfigApplicationContext(AppConfig::class.java)
    with(context.getBean<PersonRepository>()) {
        val nasta = Person(name = "Nasta", surname = "Kuzminova", age = 25)
        save(Person(name = "Peter", surname = "Sagan", age = 17))
        save(nasta)
        save(Person(name = "John", surname = "lawrence", age = 35))
        save(Person(name = "Terry", surname = "Law", age = 36))
        logger.info { "Count Person records: ${count()}" }
        findAll().forEach { logger.info { it } }
        save(nasta.apply {
            name = "Barbora"
            surname = "Spotakova"
        })
        findById(2).ifPresent { logger.info("Find by id 2: {}", it) }
        deleteById(2)
        logger.info { "Count Person records: ${count()}" }
        findOne(PersonSpecifications.NameEqualSpec("John")).ifPresent { logger.info { "Find by John is $it" } }
        findAll(PersonSpecifications.AgeBetweenSpec(20, 40)).forEach { logger.info { it } }
        deleteAll()
    }
    context.close()
}
