import org.slf4j.LoggerFactory

interface UnitsExtension
fun interface CommanderExtension : UnitsExtension {
    fun commanderReady()
}

class Commander(val units: CommanderUnits) : CommanderExtension {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun commanderReady() {
        log.info("[Commander] {} is ready!", units.name)
    }
}

fun interface SergeantExtension : UnitsExtension {
    fun sergeantReady()
}

class Sergeant(val units: SergeantUnits) : SergeantExtension {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun sergeantReady() {
        log.info("[Sergeant] {} is ready!", units.name)
    }
}

fun interface SoldierExtension : UnitsExtension {
    fun soldierReady()
}

class Soldier(val units: SoldierUnits) : SoldierExtension {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun soldierReady() {
        log.info("[Soldier] {} is ready!", units.name)
    }
}