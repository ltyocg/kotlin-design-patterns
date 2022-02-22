import org.junit.Rule
import org.junit.jupiter.migrationsupport.rules.EnableRuleMigrationSupport
import org.junit.rules.TemporaryFolder
import java.io.File
import java.io.IOException
import java.nio.file.Files
import kotlin.test.*

@EnableRuleMigrationSupport
class SimpleFileWriterTest {
    @Rule
    private val testFolder = TemporaryFolder()

    @Test
    fun `test writer not null`() {
        SimpleFileWriter(testFolder.newFile().path, ::assertNotNull)
    }

    @Test
    fun `test creates non existent file`() {
        val nonExistingFile = File(testFolder.root, "non-existing-file")
        assertFalse(nonExistingFile.exists())
        SimpleFileWriter(nonExistingFile.path, ::assertNotNull)
        assertTrue(nonExistingFile.exists())
    }

    @Test
    fun `test contents are written to file`() {
        val testMessage = "Test message"
        val temporaryFile = testFolder.newFile()
        assertTrue(temporaryFile.exists())
        SimpleFileWriter(temporaryFile.path) { it.write(testMessage) }
        assertTrue(Files.lines(temporaryFile.toPath()).allMatch(testMessage::equals))
    }

    @Test
    fun `test ripples io exception occurred while writing`() {
        val message = "Some error"
        assertFailsWith<IOException>(message) {
            SimpleFileWriter(testFolder.newFile().path) { throw IOException(message) }
        }
    }
}