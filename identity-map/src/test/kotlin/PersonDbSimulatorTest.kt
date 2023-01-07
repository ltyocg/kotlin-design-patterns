import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class PersonDbSimulatorTest {
    @Test
    fun insert() {
        val db = PersonDbSimulator()
        assertEquals(0, db.size, "Size of null database should be 0")
        db.insert(Person(1, "Thomas", 27304159))
        db.insert(Person(2, "John", 42273631))
        db.insert(Person(3, "Arthur", 27489171))
        assertEquals(3, db.size, "Incorrect size for database.")
        db.insert(Person(4, "Finn", 20499078))
        db.insert(Person(5, "Michael", 40599078))
        assertEquals(5, db.size, "Incorrect size for database.")
        db.insert(Person(5, "Kevin", 89589122))
        assertEquals(5, db.size, "Incorrect size for data base")
    }

    @Test
    fun `find not in db`() {
        val db = PersonDbSimulator()
        db.insert(Person(1, "Thomas", 27304159))
        db.insert(Person(2, "John", 42273631))
        assertFailsWith<IdNotFoundException> { db.find(3) }
    }

    @Test
    fun `find in db`() {
        val db = PersonDbSimulator()
        val person2 = Person(2, "John", 42273631)
        db.insert(Person(1, "Thomas", 27304159))
        db.insert(person2)
        assertEquals(person2, db.find(2), "Record that was found was incorrect.")
    }

    @Test
    fun `update not in db`() {
        val db = PersonDbSimulator()
        db.insert(Person(1, "Thomas", 27304159))
        db.insert(Person(2, "John", 42273631))
        assertFailsWith<IdNotFoundException> { db.update(Person(3, "Micheal", 25671234)) }
    }

    @Test
    fun `update in db`() {
        val db = PersonDbSimulator()
        db.insert(Person(1, "Thomas", 27304159))
        db.insert(Person(2, "John", 42273631))
        val person = Person(2, "Thomas", 42273690)
        db.update(person)
        assertEquals(person, db.find(2), "Incorrect update.")
    }

    @Test
    fun `delete not in db`() {
        val db = PersonDbSimulator()
        db.insert(Person(1, "Thomas", 27304159))
        db.insert(Person(2, "John", 42273631))
        assertFailsWith<IdNotFoundException> { db.delete(3) }
    }

    @Test
    fun `delete in db`() {
        val db = PersonDbSimulator()
        db.insert(Person(1, "Thomas", 27304159))
        db.insert(Person(2, "John", 42273631))
        db.delete(1)
        assertEquals(1, db.size, "Size after deletion is incorrect.")
        assertFailsWith<IdNotFoundException> { db.find(1) }
    }
}