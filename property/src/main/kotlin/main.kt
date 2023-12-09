import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    val charProto = Character().apply {
        this[Stats.STRENGTH] = 10
        this[Stats.AGILITY] = 10
        this[Stats.ARMOR] = 10
        this[Stats.ATTACK_POWER] = 10
    }
    logger.info {
        Character("Player_1", Character(Character.Type.MAGE, charProto).apply {
            this[Stats.INTELLECT] = 15
            this[Stats.SPIRIT] = 10
        }).apply { this[Stats.ARMOR] = 8 }
    }
    logger.info {
        Character("Player_2", Character(Character.Type.WARRIOR, charProto).apply {
            this[Stats.RAGE] = 15
            this[Stats.ARMOR] = 15
        })
    }
    val rogue = Character("Player_3", Character(Character.Type.ROGUE, charProto).apply {
        this[Stats.ENERGY] = 15
        this[Stats.AGILITY] = 15
    })
    logger.info { rogue }
    logger.info { Character("Player_4", rogue).apply { this[Stats.ATTACK_POWER] = 12 } }
}
