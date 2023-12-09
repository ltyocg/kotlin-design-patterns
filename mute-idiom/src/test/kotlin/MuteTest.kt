import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertFailsWith

class MuteTest {
    private val logger = KotlinLogging.logger {}
    private val message = "should not occur"

    @Test
    fun `mute should run the checked runnable and not throw any exception if checked runnable does not throw any exception`() =
        Mute.mute { methodNotThrowingAnyException() }

    @Test
    fun `mute should rethrow unexpected exception as AssertionError`() {
        assertFailsWith<AssertionError> { Mute.mute { methodThrowingException() } }
    }

    @Test
    fun `logged mute should run the checked runnable and not throw any exception if checked runnable does not throw any exception`() {
        Mute.mute { methodNotThrowingAnyException() }
    }

    @Test
    fun `logged mute should log exception trace before swallowing it`() {
        val stream = ByteArrayOutputStream()
        System.setErr(PrintStream(stream))
        Mute.loggedMute { methodThrowingException() }
        assertContains(stream.toString(), message)
    }

    private fun methodNotThrowingAnyException() = logger.info { "Executed successfully" }
    private fun methodThrowingException(): Nothing = throw Exception(message)
}
