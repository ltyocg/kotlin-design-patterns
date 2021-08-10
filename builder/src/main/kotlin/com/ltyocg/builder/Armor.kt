package com.ltyocg.builder

enum class Armor(private val title: String) {
    CLOTHES("clothes"),
    LEATHER("leather"),
    CHAIN_MAIL("chain mail"),
    PLATE_MAIL("plate mail");

    override fun toString(): String = title
}