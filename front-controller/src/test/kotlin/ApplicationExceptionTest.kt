import kotlin.test.Test
import kotlin.test.assertSame

class ApplicationExceptionTest {
    @Test
    fun `test cause`() {
        val cause = Exception()
        assertSame(cause, ApplicationException(cause).cause)
    }
}