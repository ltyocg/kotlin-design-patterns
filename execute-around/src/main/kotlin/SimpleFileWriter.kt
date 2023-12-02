import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.FileWriter

class SimpleFileWriter(filename: String, action: FileWriterAction) {
    private val logger = KotlinLogging.logger {}

    init {
        logger.info { "Opening the file" }
        FileWriter(filename).use {
            logger.info { "Executing the action" }
            action.writeFile(it)
            logger.info { "Closing the file" }
        }
    }
}
