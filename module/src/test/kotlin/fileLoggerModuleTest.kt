import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.File
import java.io.IOException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

private const val OUTPUT_FILE = "output.txt"
private const val ERROR_FILE = "error.txt"
private const val MESSAGE = "MESSAGE"
private const val ERROR = "ERROR"

class FileLoggerModuleTest {
    private val logger = KotlinLogging.logger {}

    @Test
    fun `file message`() = FileLoggerModule.execute {
        printString(MESSAGE)
        assertEquals(readFirstLine(OUTPUT_FILE), MESSAGE)
    }

    @Test
    fun `no file message`() = FileLoggerModule.execute {
        assertNull(readFirstLine(OUTPUT_FILE))
    }

    @Test
    fun testFileErrorMessage() = FileLoggerModule.execute {
        printErrorString(ERROR)
        assertEquals(ERROR, readFirstLine(ERROR_FILE))
    }

    @Test
    fun testNoFileErrorMessage() = FileLoggerModule.execute {
        assertNull(readFirstLine(ERROR_FILE))
    }

    private fun FileLoggerModule.execute(block: FileLoggerModule.() -> Unit) {
        prepare()
        block()
        unprepare()
    }

    private fun readFirstLine(file: String): String? = try {
        File(file).useLines { it.firstOrNull() }
            .also { logger.info { "ModuleTest::readFirstLine() : firstLine : $it" } }
    } catch (e: IOException) {
        logger.error(e) { "ModuleTest::readFirstLine()" }
        null
    }
}
