import org.slf4j.LoggerFactory

class IdentityMap {
    private val log = LoggerFactory.getLogger(javaClass)
    val personMap = mutableMapOf<Int, Person>()
    fun addPerson(person: Person) =
        if (personMap.containsKey(person.personNationalId)) log.info("Key already in Map")
        else personMap[person.personNationalId] = person

    fun getPerson(id: Int): Person? {
        val person = personMap[id]
        return if (person == null) {
            log.info("ID not in Map.")
            null
        } else {
            log.info(person.toString())
            person
        }
    }

    val size: Int
        get() = personMap.size
}