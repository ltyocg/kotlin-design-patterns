import io.github.oshai.kotlinlogging.KotlinLogging

interface UnitsExtension
fun interface CommanderExtension : UnitsExtension {
    fun commanderReady()
}

class Commander(val units: CommanderUnits) : CommanderExtension {
    private val logger = KotlinLogging.logger {}
    override fun commanderReady() = logger.info { "[Commander] ${units.name} is ready!" }
}

fun interface SergeantExtension : UnitsExtension {
    fun sergeantReady()
}

class Sergeant(val units: SergeantUnits) : SergeantExtension {
    private val logger = KotlinLogging.logger {}
    override fun sergeantReady() = logger.info { "[Sergeant] ${units.name} is ready!" }
}

fun interface SoldierExtension : UnitsExtension {
    fun soldierReady()
}

class Soldier(val units: SoldierUnits) : SoldierExtension {
    private val logger = KotlinLogging.logger {}
    override fun soldierReady() = logger.info { "[Soldier] ${units.name} is ready!" }
}
