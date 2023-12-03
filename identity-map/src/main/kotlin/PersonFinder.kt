import io.github.oshai.kotlinlogging.KotlinLogging

class PersonFinder {
    private val logger = KotlinLogging.logger {}
    val identityMap = IdentityMap()
    var db = PersonDbSimulator()
    fun getPerson(key: Int): Person {
        var person = identityMap.getPerson(key)
        return if (person != null) {
            logger.info { "Person found in the Map" }
            person
        } else {
            person = db.find(key)
            identityMap.addPerson(person)
            logger.info { "Person found in DB." }
            person
        }
    }
}
