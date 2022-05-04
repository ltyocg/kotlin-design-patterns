import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    log.info("elf kingdom")
    createKingdom(KingdomType.ELF).log()
    log.info("orc kingdom")
    createKingdom(KingdomType.ORC).log()
}

fun createKingdom(kingdomType: KingdomType): Kingdom =
    KingdomFactory.makeFactory(kingdomType).let { Kingdom(it.createKing(), it.createCastle(), it.createArmy()) }

private fun Kingdom.log() {
    log.info(army.description)
    log.info(castle.description)
    log.info(king.description)
}