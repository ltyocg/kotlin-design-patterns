package com.ltyocg.abstractfactory

import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")

fun main() {
    log.info("elf kingdom")
    createKingdom(KingdomType.ELF).print()
    log.info("orc kingdom")
    createKingdom(KingdomType.ORC).print()
}

fun createKingdom(kingdomType: KingdomType): Kingdom {
    val kingdomFactory = makeFactory(kingdomType)
    return Kingdom(
        kingdomFactory.createKing(),
        kingdomFactory.createCastle(),
        kingdomFactory.createArmy()
    )
}

private fun Kingdom.print() {
    log.info(army.description)
    log.info(castle.description)
    log.info(king.description)
}