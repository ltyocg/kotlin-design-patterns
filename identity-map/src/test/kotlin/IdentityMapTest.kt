import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class IdentityMapTest {
    @Test
    fun `add to map`() {
        val idMap = IdentityMap().apply {
            addPerson(Person(11, "Michael", 27304159))
            addPerson(Person(22, "John", 42273631))
            addPerson(Person(33, "Arthur", 27489171))
            addPerson(Person(44, "Finn", 20499078))
            addPerson(Person(11, "Michael", 40599078))
        }
        assertEquals(4, idMap.size, "Size of the map is incorrect")
        assertEquals(27304159, idMap.getPerson(11)!!.phoneNum, "Incorrect return value for phone number")
    }

    @Test
    fun `get from map`() {
        val person1 = Person(11, "Michael", 27304159)
        val person4 = Person(44, "Finn", 20499078)
        val idMap = IdentityMap().apply {
            addPerson(person1)
            addPerson(Person(22, "John", 42273631))
            addPerson(Person(33, "Arthur", 27489171))
            addPerson(person4)
            addPerson(Person(55, "Michael", 40599078))
        }
        assertEquals(person1, idMap.getPerson(11), "Incorrect person record returned")
        assertEquals(person4, idMap.getPerson(44), "Incorrect person record returned")
        assertNull(idMap.getPerson(1), "Incorrect person record returned")
    }
}