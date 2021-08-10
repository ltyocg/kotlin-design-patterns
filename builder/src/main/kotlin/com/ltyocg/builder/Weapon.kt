package com.ltyocg.builder

enum class Weapon {
    DAGGER, SWORD, AXE, WARHAMMER, BOW;

    override fun toString(): String = name.lowercase()
}
