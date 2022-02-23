import kotlin.test.Test
import kotlin.test.assertEquals

private const val MSG = "test"

class ExceptionsTest {
    @Test
    fun exception() = try {
        throw LockingException(MSG)
    } catch (e: LockingException) {
        assertEquals(MSG, e.localizedMessage)
    }
}