import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class PersonTest {
    @Test
    fun equality() {
        val person2 = Person(2, "Kane", 989920011)
        assertNotEquals(Person(1, "Harry", 989950022), person2, "Incorrect equality condition")
        assertEquals(person2, Person(2, "John", 789012211), "Incorrect inequality condition")
    }
}