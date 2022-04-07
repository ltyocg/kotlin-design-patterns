package repository

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.getBean
import org.springframework.context.annotation.AnnotationConfigApplicationContext

private val log = LoggerFactory.getLogger("main")
fun main() {
    val context = AnnotationConfigApplicationContext(AppConfig::class.java)
    with(context.getBean<PersonRepository>()) {
        val nasta = Person(name = "Nasta", surname = "Kuzminova", age = 25)
        save(Person(name = "Peter", surname = "Sagan", age = 17))
        save(nasta)
        save(Person(name = "John", surname = "lawrence", age = 35))
        save(Person(name = "Terry", surname = "Law", age = 36))
        log.info("Count Person records: {}", count())
        findAll().asSequence().map(Person::toString).forEach(log::info)
        save(nasta.apply {
            name = "Barbora"
            surname = "Spotakova"
        })
        findById(2).ifPresent { log.info("Find by id 2: {}", it) }
        deleteById(2)
        log.info("Count Person records: {}", count())
        findOne(PersonSpecifications.NameEqualSpec("John")).ifPresent { log.info("Find by John is {}", it) }
        findAll(PersonSpecifications.AgeBetweenSpec(20, 40)).asSequence().map(Person::toString).forEach(log::info)
        deleteAll()
    }
    context.close()
}
