import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.PrintStream

object ConsoleLoggerModule {
    private val logger = KotlinLogging.logger {}
    private lateinit var output: PrintStream
    private lateinit var error: PrintStream

    fun prepare(): ConsoleLoggerModule = apply {
        logger.debug { "ConsoleLoggerModule::prepare()" }
        output = PrintStream(System.out)
        error = PrintStream(System.err)
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
        logger.debug { "ConsoleLoggerModule::unprepare()" }
    }

    fun printString(value: String?) = output.println(value)
    fun printErrorString(value: String?) = error.println(value)
}
