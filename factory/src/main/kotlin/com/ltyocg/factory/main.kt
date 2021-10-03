package com.ltyocg.factory

import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")

fun main() {
    log.info("The alchemist begins his work.")
    log.info(CoinFactory.getCoin(CoinType.COPPER).description)
    log.info(CoinFactory.getCoin(CoinType.GOLD).description)
}