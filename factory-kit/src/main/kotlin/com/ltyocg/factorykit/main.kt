package com.ltyocg.factorykit

import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    val factory = WeaponFactory.factory {
        it.add(WeaponType.AXE, ::Axe)
        it.add(WeaponType.BOW, ::Bow)
        it.add(WeaponType.SPEAR, ::Spear)
        it.add(WeaponType.SWORD, ::Sword)
    }
    sequenceOf(WeaponType.AXE, WeaponType.SPEAR, WeaponType.SWORD, WeaponType.BOW)
        .map(factory::create)
        .map(Weapon::toString)
        .forEach(log::info)
}