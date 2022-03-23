import org.slf4j.LoggerFactory
import java.io.PrintStream

object ConsoleLoggerModule {
    private val log = LoggerFactory.getLogger(javaClass)
    private var output: PrintStream? = null
    private var error: PrintStream? = null

    fun prepare(): ConsoleLoggerModule {
        log.debug("ConsoleLoggerModule::prepare()")
        output = PrintStream(System.out)
        error = PrintStream(System.err)
        return this
    }

    fun unprepare() {
        output?.let {
            it.flush()
            it.close()
        }
        error?.let {
            it.flush()
            it.close()
        }
        log.debug("ConsoleLoggerModule::unprepare()")
    }

    fun printString(value: String?) = output!!.println(value)
    fun printErrorString(value: String?) = error!!.println(value)
}