import org.slf4j.LoggerFactory

class PersonFinder {
    private val log = LoggerFactory.getLogger(javaClass)
    val identityMap = IdentityMap()
    var db = PersonDbSimulator()
    fun getPerson(key: Int): Person {
        var person = identityMap.getPerson(key)
        return if (person != null) {
            log.info("Person found in the Map")
            person
        } else {
            person = db.find(key)
            identityMap.addPerson(person)
            log.info("Person found in DB.")
            person
        }
    }
}