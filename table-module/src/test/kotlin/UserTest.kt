import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class UserTest {
    @Test
    fun `test equals`() {
        assertEquals(
            User(1, "janedoe", "iloveyou"),
            User(1, "janedoe", "iloveyou")
        )
        assertNotEquals(
            User(123, "janedoe", "iloveyou"),
            User(1, "janedoe", "iloveyou")
        )
        assertNotEquals(
            User(1, null, "iloveyou"),
            User(1, "janedoe", "iloveyou")
        )
        assertNotEquals(
            User(1, "iloveyou", "iloveyou"),
            User(1, "janedoe", "iloveyou")
        )
        assertNotEquals(
            User(1, "janedoe", "janedoe"),
            User(1, "janedoe", "iloveyou")
        )
        assertNotEquals(
            User(1, "janedoe", null),
            User(1, "janedoe", "iloveyou")
        )
        assertEquals(
            User(1, null, "iloveyou"),
            User(1, null, "iloveyou")
        )
        assertEquals(
            User(1, "janedoe", null),
            User(1, "janedoe", null)
        )
    }

    @Test
    fun `test hashCode`() {
        assertEquals(-2139001127, User(1, "janedoe", "iloveyou").hashCode())
        assertEquals(-1332417883, User(1, null, "iloveyou").hashCode())
        assertEquals(-806582283, User(1, "janedoe", null).hashCode())
    }

    @Test
    fun id() {
        val user = User(1, "janedoe", "iloveyou")
        user.id = 2
        assertEquals(2, user.id)
    }

    @Test
    fun password() {
        val user = User(1, "janedoe", "tmp")
        user.password = "iloveyou"
        assertEquals("iloveyou", user.password)
    }

    @Test
    fun username() {
        val user = User(1, "tmp", "iloveyou")
        user.username = "janedoe"
        assertEquals("janedoe", user.username)
    }

    @Test
    fun `test toString`() {
        val user = User(1, "janedoe", "iloveyou")
        assertEquals("User(id=${user.id}, username=${user.username}, password=${user.password})", user.toString())
    }
}