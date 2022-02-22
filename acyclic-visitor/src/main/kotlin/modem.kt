import org.slf4j.LoggerFactory

interface Modem {
    fun accept(modemVisitor: ModemVisitor)
}

class Hayes : Modem {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun accept(modemVisitor: ModemVisitor) {
        if (modemVisitor is HayesVisitor) modemVisitor.visit(this)
        else log.info("Only HayesVisitor is allowed to visit Hayes modem")
    }

    override fun toString(): String = "Hayes modem"
}

class Zoom : Modem {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun accept(modemVisitor: ModemVisitor) {
        if (modemVisitor is ZoomVisitor) modemVisitor.visit(this)
        else log.info("Only HayesVisitor is allowed to visit Zoom modem")
    }

    override fun toString(): String = "Zoom modem"
}