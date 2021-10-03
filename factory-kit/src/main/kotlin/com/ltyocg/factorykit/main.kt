package com.ltyocg.factorykit

import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")

fun main() {
    log.info(WeaponFactory.factory {
        it.add(WeaponType.AXE, ::Axe)
        it.add(WeaponType.BOW, ::Bow)
        it.add(WeaponType.SPEAR, ::Spear)
        it.add(WeaponType.SWORD, ::Sword)
    }.create(WeaponType.AXE).toString())
}