package com.ltyocg.facade

import org.slf4j.LoggerFactory

abstract class DwarvenMineWorker {
    private val log = LoggerFactory.getLogger(this::class.java)
    fun goToSleep() {
        log.info("{} goes to sleep.", name)
    }

    fun wakeUp() {
        log.info("{} wakes up.", name)
    }

    fun goHome() {
        log.info("{} goes home.", name)
    }

    fun goToMine() {
        log.info("{} goes to the mine.", name)
    }

    abstract val name: String
    fun action(vararg actions: Action) {
        actions.forEach {
            when (it) {
                Action.GO_TO_SLEEP -> goToSleep()
                Action.WAKE_UP -> wakeUp()
                Action.GO_HOME -> goHome()
                Action.GO_TO_MINE -> goToMine()
                Action.WORK -> work()
            }
        }
    }

    abstract fun work()
    enum class Action {
        GO_TO_SLEEP, WAKE_UP, GO_HOME, GO_TO_MINE, WORK
    }
}

class DwarvenCartOperator : DwarvenMineWorker() {
    private val log = LoggerFactory.getLogger(this::class.java)
    override val name = "Dwarf cart operator"
    override fun work() {
        log.info("{} moves gold chunks out of the mine.", name)
    }
}

class DwarvenGoldDigger : DwarvenMineWorker() {
    private val log = LoggerFactory.getLogger(this::class.java)
    override val name = "Dwarf gold digger"
    override fun work() {
        log.info("{} digs for gold.", name)
    }
}

class DwarvenTunnelDigger : DwarvenMineWorker() {
    private val log = LoggerFactory.getLogger(this::class.java)
    override val name = "Dwarven tunnel digger"
    override fun work() {
        log.info("{} creates another promising tunnel.", name)
    }
}
