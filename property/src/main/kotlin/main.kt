import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    val charProto = Character().apply {
        this[Stats.STRENGTH] = 10
        this[Stats.AGILITY] = 10
        this[Stats.ARMOR] = 10
        this[Stats.ATTACK_POWER] = 10
    }
    log.info(Character("Player_1", Character(Character.Type.MAGE, charProto).apply {
        this[Stats.INTELLECT] = 15
        this[Stats.SPIRIT] = 10
    }).apply { this[Stats.ARMOR] = 8 }.toString())
    log.info(Character("Player_2", Character(Character.Type.WARRIOR, charProto).apply {
        this[Stats.RAGE] = 15
        this[Stats.ARMOR] = 15
    }).toString())
    val rogue = Character("Player_3", Character(Character.Type.ROGUE, charProto).apply {
        this[Stats.ENERGY] = 15
        this[Stats.AGILITY] = 15
    })
    log.info(rogue.toString())
    log.info(Character("Player_4", rogue).apply { this[Stats.ATTACK_POWER] = 12 }.toString())
}