import io.github.oshai.kotlinlogging.KotlinLogging

class IdentityMap {
    private val logger = KotlinLogging.logger {}
    val personMap = mutableMapOf<Int, Person>()
    fun addPerson(person: Person) =
        if (personMap.containsKey(person.personNationalId)) logger.info { "Key already in Map" }
        else personMap[person.personNationalId] = person

    fun getPerson(id: Int): Person? {
        val person = personMap[id]
        return if (person == null) {
            logger.info { "ID not in Map." }
            null
        } else {
            logger.info { person }
            person
        }
    }

    val size: Int
        get() = personMap.size
}
