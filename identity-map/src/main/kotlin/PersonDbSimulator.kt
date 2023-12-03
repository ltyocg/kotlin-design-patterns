import io.github.oshai.kotlinlogging.KotlinLogging

class PersonDbSimulator {
    private val logger = KotlinLogging.logger {}
    private val personList = mutableListOf<Person>()
    fun find(personNationalId: Int): Person {
        val elem = firstOrNull(personNationalId) ?: idNotFound(personNationalId)
        logger.info { elem }
        return elem
    }

    fun insert(person: Person) {
        val elem = firstOrNull(person.personNationalId)
        if (elem != null) logger.info { "Record already exists." }
        else personList.add(person)
    }

    fun update(person: Person) {
        val elem = firstOrNull(person.personNationalId) ?: idNotFound(person.personNationalId)
        elem.apply {
            name = person.name
            phoneNum = person.phoneNum
        }
        logger.info { "Record updated successfully" }
    }

    fun delete(id: Int) {
        val elem = firstOrNull(id) ?: idNotFound(id)
        personList.remove(elem)
        logger.info { "Record deleted successfully." }
    }

    val size: Int
        get() = personList.size

    private fun firstOrNull(personNationalId: Int): Person? = personList.firstOrNull { it.personNationalId == personNationalId }
    private fun idNotFound(id: Int): Nothing = throw IdNotFoundException("ID : $id not in DataBase")
}
