package com.ltyocg.decorator

import org.slf4j.LoggerFactory

interface Troll {
    fun attack()
    val attackPower: Int
    fun fleeBattle()
}

class ClubbedTroll(private val decorated: Troll) : Troll {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun attack() {
        decorated.attack()
        log.info("The troll swings at you with a club!")
    }

    override val attackPower: Int
        get() = decorated.attackPower + 10

    override fun fleeBattle() {
        decorated.fleeBattle()
    }
}

class SimpleTroll : Troll {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun attack() {
        log.info("The troll tries to grab you!")
    }

    override val attackPower: Int
        get() = 10

    override fun fleeBattle() {
        log.info("The troll shrieks in horror and runs away!")
    }
}