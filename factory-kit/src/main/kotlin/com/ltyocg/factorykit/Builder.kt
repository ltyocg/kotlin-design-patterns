package com.ltyocg.factorykit

fun interface Builder {
    fun add(name: WeaponType, supplier: () -> Weapon)
}