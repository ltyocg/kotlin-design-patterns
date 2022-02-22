import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")

fun main() {
    arrayOf(SoldierUnits("SoldierUnits1"), SergeantUnits("SergeantUnits1"), CommanderUnits("CommanderUnits1"))
        .forEach {
            val func = { e: String -> { log.info("{} without {}", it.name, e) } }
            "SoldierExtension".run { (it.getUnitsExtension(this) as? SoldierExtension)?.soldierReady() ?: func(this) }
            "SergeantExtension".run { (it.getUnitsExtension(this) as? SergeantExtension)?.sergeantReady() ?: func(this) }
            "CommanderExtension".run { (it.getUnitsExtension(this) as? CommanderExtension)?.commanderReady() ?: func(this) }
        }
}
