package com.ltyocg.factory.method

enum class WeaponType(private val title: String) {
    SHORT_SWORD("short sword"),
    SPEAR("spear"),
    AXE("axe"),
    UNDEFINED("");

    override fun toString(): String = title
}