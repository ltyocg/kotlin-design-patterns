package com.ltyocg.builder

enum class HairColor {
    WHITE, BLOND, RED, BROWN, BLACK;

    override fun toString(): String = name.lowercase()
}