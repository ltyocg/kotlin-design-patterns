package com.ltyocg.bridge

import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")

fun main() {
    log.info("The knight receives an enchanted sword.")
    with(Sword(SoulEatingEnchantment())) {
        wield()
        swing()
        unwield()
    }
    log.info("The valkyrie receives an enchanted hammer.")
    with(Hammer(FlyingEnchantment())) {
        wield()
        swing()
        unwield()
    }
}