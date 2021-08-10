package com.ltyocg.builder

enum class HairType(private val title: String) {
    BALD("bald"),
    SHORT("short"),
    CURLY("curly"),
    LONG_STRAIGHT("long straight"),
    LONG_CURLY("long curly");

    override fun toString(): String = title
}