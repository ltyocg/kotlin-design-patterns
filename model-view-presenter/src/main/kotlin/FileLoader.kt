import org.slf4j.LoggerFactory
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.Serializable

class FileLoader : Serializable {
    lateinit var fileName: String
    private val log = LoggerFactory.getLogger(javaClass)
    var isLoaded = false
    fun loadData(): String? = try {
        File(fileName)
            .let(::FileReader)
            .let(::BufferedReader)
            .use { return it.lineSequence().joinToString("\n").also { isLoaded = true } }
    } catch (e: Exception) {
        log.error("File {} does not exist", fileName)
        null
    }

    fun fileExists(): Boolean = File(fileName).exists()
}