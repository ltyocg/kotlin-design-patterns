import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.File
import java.util.*

private val logger = KotlinLogging.logger {}
fun main() {
    SimpleFileWriter("testfile.txt") {
        it.write("Gandalf was here")
    }
    Scanner(File("testfile.txt")).use {
        it.useDelimiter(System.getProperty("line.separator")).forEach { logger.info { it } }
    }
}
