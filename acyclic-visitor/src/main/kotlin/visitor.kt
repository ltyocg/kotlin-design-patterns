import io.github.oshai.kotlinlogging.KotlinLogging

interface ModemVisitor
interface HayesVisitor : ModemVisitor {
    fun visit(hayes: Hayes)
}

interface ZoomVisitor : ModemVisitor {
    fun visit(zoom: Zoom)
}

interface AllModemVisitor : HayesVisitor, ZoomVisitor
class ConfigureForDosVisitor : AllModemVisitor {
    private val logger = KotlinLogging.logger {}
    override fun visit(hayes: Hayes) = logger.info { "$hayes used with Dos configurator." }
    override fun visit(zoom: Zoom) = logger.info { "$zoom used with Dos configurator." }
}

class ConfigureForUnixVisitor : ZoomVisitor {
    private val logger = KotlinLogging.logger {}
    override fun visit(zoom: Zoom) = logger.info { "$zoom used with Unix configurator." }
}
