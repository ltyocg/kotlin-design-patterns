import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    logger.info { "elf kingdom" }
    createKingdom(KingdomType.ELF).apply {
        logger.info { army.description }
        logger.info { castle.description }
        logger.info { king.description }
    }
    logger.info { "orc kingdom" }
    createKingdom(KingdomType.ORC).apply {
        logger.info { army.description }
        logger.info { castle.description }
        logger.info { king.description }
    }
}

fun createKingdom(kingdomType: KingdomType): Kingdom =
    KingdomFactory.makeFactory(kingdomType).let { Kingdom(it.createKing(), it.createCastle(), it.createArmy()) }
