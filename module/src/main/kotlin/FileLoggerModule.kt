import org.slf4j.LoggerFactory
import java.io.FileOutputStream
import java.io.PrintStream

object FileLoggerModule {
    private val log = LoggerFactory.getLogger(javaClass)
    private var output: PrintStream? = null
    private var error: PrintStream? = null

    fun prepare(): FileLoggerModule {
        log.debug("FileLoggerModule::prepare()")
        output = PrintStream(FileOutputStream("output.txt"))
        error = PrintStream(FileOutputStream("error.txt"))
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
        log.debug("FileLoggerModule::unprepare()")
    }

    fun printString(value: String?) = output!!.println(value)
    fun printErrorString(value: String?) = error!!.println(value)
}