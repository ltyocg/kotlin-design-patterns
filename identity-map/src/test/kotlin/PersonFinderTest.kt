import kotlin.test.*

class PersonFinderTest {
    @Test
    fun `person found in db`() {
        val personList = personList()
        val personFinder = PersonFinder().apply {
            db = PersonDbSimulator().apply {
                personList.forEach(::insert)
            }
        }
        personList.forEach {
            assertEquals(it, personFinder.getPerson(it.personNationalId), "Find person returns incorrect record.")
        }
    }

    @Test
    fun `person found in id map`() {
        val personList = personList()
        val personFinder = PersonFinder().apply {
            db = PersonDbSimulator().apply {
                personList.forEach(::insert)
            }
        }
        assertFalse(personFinder.identityMap.personMap.containsKey(3))
        val person3 = personList[2]
        assertEquals(person3, personFinder.getPerson(3), "Finder returns incorrect record.")
        assertTrue(personFinder.identityMap.personMap.containsKey(3))
        assertEquals(person3, personFinder.getPerson(3), "Finder returns incorrect record.")
    }

    @Test
    fun `person not found in db`() {
        val db = PersonDbSimulator()
        val personFinder = PersonFinder().apply { this.db = db }
        assertFailsWith<IdNotFoundException> { personFinder.getPerson(1) }
        val personList = personList()
        personList.forEach(db::insert)
        personFinder.db = db
        assertEquals(personList[3], personFinder.getPerson(4), "Find returns incorrect record")
        personFinder.getPerson(1)
        assertFailsWith<IdNotFoundException> { personFinder.getPerson(6) }
    }

    private fun personList(): List<Person> = listOf(
        Person(1, "John", 27304159),
        Person(2, "Thomas", 42273631),
        Person(3, "Arthur", 27489171),
        Person(4, "Finn", 20499078),
        Person(5, "Michael", 40599078)
    )
}