import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.FileOutputStream
import java.io.PrintStream

object FileLoggerModule {
    private val logger = KotlinLogging.logger {}
    private lateinit var output: PrintStream
    private lateinit var error: PrintStream

    fun prepare(): FileLoggerModule = apply {
        logger.debug { "FileLoggerModule::prepare()" }
        output = PrintStream(FileOutputStream("output.txt"))
        error = PrintStream(FileOutputStream("error.txt"))
    }

    fun unprepare() {
        with(output) {
            flush()
            close()
        }
        with(error) {
            flush()
            close()
        }
        logger.debug { "FileLoggerModule::unprepare()" }
    }

    fun printString(value: String?) = output.println(value)
    fun printErrorString(value: String?) = error.println(value)
}
