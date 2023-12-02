import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    arrayOf(SoldierUnits("SoldierUnits1"), SergeantUnits("SergeantUnits1"), CommanderUnits("CommanderUnits1"))
        .forEach {
            val func = { e: String -> { logger.info { "${it.name} without $e" } } }
            "SoldierExtension".run { (it.getUnitsExtension(this) as? SoldierExtension)?.soldierReady() ?: func(this) }
            "SergeantExtension".run { (it.getUnitsExtension(this) as? SergeantExtension)?.sergeantReady() ?: func(this) }
            "CommanderExtension".run { (it.getUnitsExtension(this) as? CommanderExtension)?.commanderReady() ?: func(this) }
        }
}
