import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class CakeBakingExceptionTest {
    @Test
    fun `test constructor`() {
        with(CakeBakingException()) {
            assertNull(message)
            assertNull(cause)
        }
    }

    @Test
    fun `test constructor with message`() {
        val expectedMessage = "message"
        with(CakeBakingException(expectedMessage)) {
            assertEquals(expectedMessage, message)
            assertNull(cause)
        }
    }
}