package com.ltyocg.abstractfactory

import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
val kingdom = Kingdom()

fun main() {
    log.info("elf kingdom")
    createKingdom(Kingdom.KingdomType.ELF)
    log.info(kingdom.army.description)
    log.info(kingdom.castle.description)
    log.info(kingdom.king.description)
    log.info("orc kingdom")
    createKingdom(Kingdom.KingdomType.ORC)
    log.info(kingdom.army.description)
    log.info(kingdom.castle.description)
    log.info(kingdom.king.description)
}

fun createKingdom(kingdomType: Kingdom.KingdomType) {
    val kingdomFactory = Kingdom.makeFactory(kingdomType)
    kingdom.apply {
        king = kingdomFactory.createKing()
        castle = kingdomFactory.createCastle()
        army = kingdomFactory.createArmy()
    }
}