import kotlin.test.Test
import kotlin.test.assertEquals

class ServerTest {
    @Test
    fun getSession() = assertEquals("Session", Server("localhost", 8080).getSession("Session").clientName)
}
