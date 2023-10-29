import io.github.oshai.kotlinlogging.KotlinLogging

interface Modem {
    fun accept(modemVisitor: ModemVisitor)
}

class Hayes : Modem {
    private val logger = KotlinLogging.logger {}
    override fun accept(modemVisitor: ModemVisitor) =
        if (modemVisitor is HayesVisitor) modemVisitor.visit(this)
        else logger.info { "Only HayesVisitor is allowed to visit Hayes modem" }

    override fun toString(): String = "Hayes modem"
}

class Zoom : Modem {
    private val logger = KotlinLogging.logger {}
    override fun accept(modemVisitor: ModemVisitor) =
        if (modemVisitor is ZoomVisitor) modemVisitor.visit(this)
        else logger.info { "Only HayesVisitor is allowed to visit Zoom modem" }

    override fun toString(): String = "Zoom modem"
}
