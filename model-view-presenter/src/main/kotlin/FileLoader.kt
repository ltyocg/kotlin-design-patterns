import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.Serializable

class FileLoader : Serializable {
    lateinit var fileName: String
    private val logger = KotlinLogging.logger {}
    var isLoaded = false
    fun loadData(): String? = try {
        File(fileName)
            .let(::FileReader)
            .let(::BufferedReader)
            .use {
                try {
                    return it.lineSequence().joinToString("\n")
                } finally {
                    isLoaded = true
                }
            }
    } catch (e: Exception) {
        logger.error { "File $fileName does not exist" }
        null
    }

    fun fileExists(): Boolean = File(fileName).exists()
}
