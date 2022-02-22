import org.slf4j.LoggerFactory

interface Printer {
    fun print(message: String)
}

class CanonPrinter : Printer {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun print(message: String) {
        log.info("Canon Printer : {}", message)
    }
}

class EpsonPrinter : Printer {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun print(message: String) {
        log.info("Epson Printer : {}", message)
    }
}

class HpPrinter : Printer {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun print(message: String) {
        log.info("HP Printer : {}", message)
    }
}

class PrinterController(private val printer: Printer) : Printer {
    override fun print(message: String) {
        printer.print(message)
    }
}