import io.github.oshai.kotlinlogging.KotlinLogging

interface Printer {
    fun print(message: String)
}

class CanonPrinter : Printer {
    private val logger = KotlinLogging.logger {}
    override fun print(message: String) = logger.info { "Canon Printer : $message" }
}

class EpsonPrinter : Printer {
    private val logger = KotlinLogging.logger {}
    override fun print(message: String) = logger.info { "Epson Printer : $message" }
}

class HpPrinter : Printer {
    private val logger = KotlinLogging.logger {}
    override fun print(message: String) = logger.info { "HP Printer : $message" }
}

class PrinterController(private val printer: Printer) : Printer {
    override fun print(message: String) = printer.print(message)
}
