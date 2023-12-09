import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.*
import java.net.URL

object Utility {
    private val logger = KotlinLogging.logger {}
    fun characterFrequency(fileLocation: String): Map<Char, Long> = try {
        BufferedReader(FileReader(fileLocation)).use { bufferedReader ->
            bufferedReader.lineSequence()
                .flatMap { it.asSequence() }
                .fold(mutableMapOf()) { acc, c ->
                    acc.also { it[c] = it.getOrDefault(c, 0) + 1 }
                }
        }
    } catch (e: IOException) {
        e.printStackTrace()
        emptyMap()
    }

    fun lowestFrequencyChar(charFrequency: Map<Char, Long>): Char = charFrequency.asSequence()
        .minByOrNull { it.value }
        ?.key ?: throw NoSuchElementException("No value present")

    fun countLines(fileLocation: String): Int = try {
        BufferedReader(FileReader(fileLocation)).use { bufferedReader ->
            bufferedReader.lines().count().toInt()
        }
    } catch (e: IOException) {
        e.printStackTrace()
        0
    }

    fun downloadFile(urlString: String?): String {
        logger.info { "Downloading contents from url: $urlString" }
        val file = File.createTempFile("promise_pattern", null)
        BufferedReader(InputStreamReader(URL(urlString).openStream())).use { bufferedReader ->
            FileWriter(file).use { writer ->
                bufferedReader.lineSequence().forEach {
                    writer.write(it)
                    writer.write("\n")
                }
                logger.info { "File downloaded at: ${file.absolutePath}" }
                return file.absolutePath
            }
        }
    }
}
