package com.ltyocg.command

import org.slf4j.LoggerFactory

abstract class Target {
    private val log = LoggerFactory.getLogger(javaClass)
    var size: Size = Size.NORMAL
        private set
    var visibility: Visibility = Visibility.VISIBLE
        private set

    fun printStatus() {
        log.info("{}, [size={}] [visibility={}]", this, size, visibility)
    }

    fun changeSize() {
        size = if (size == Size.NORMAL) Size.SMALL else Size.NORMAL
    }

    fun changeVisibility() {
        visibility = if (visibility == Visibility.INVISIBLE) Visibility.VISIBLE else Visibility.INVISIBLE
    }
}

class Goblin : Target() {
    override fun toString(): String = "Goblin"
}