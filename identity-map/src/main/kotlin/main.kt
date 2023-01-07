import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    val finder = PersonFinder().apply {
        db = PersonDbSimulator().apply {
            insert(Person(1, "John", 27304159))
            insert(Person(2, "Thomas", 42273631))
            insert(Person(3, "Arthur", 27489171))
            insert(Person(4, "Finn", 20499078))
            insert(Person(5, "Michael", 40599078))
        }
    }
    arrayOf(2, 4, 5, 2).forEach { log.info(finder.getPerson(it).toString()) }
}