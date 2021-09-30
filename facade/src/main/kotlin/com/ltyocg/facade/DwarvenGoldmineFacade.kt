package com.ltyocg.facade

class DwarvenGoldmineFacade {
    private val workers = listOf(DwarvenGoldDigger(), DwarvenCartOperator(), DwarvenTunnelDigger())
    fun startNewDay() {
        makeActions(workers, DwarvenMineWorker.Action.WAKE_UP, DwarvenMineWorker.Action.GO_TO_MINE)
    }

    fun digOutGold() {
        makeActions(workers, DwarvenMineWorker.Action.WORK)
    }

    fun endDay() {
        makeActions(workers, DwarvenMineWorker.Action.GO_HOME, DwarvenMineWorker.Action.GO_TO_SLEEP)
    }

    private fun makeActions(workers: Collection<DwarvenMineWorker>, vararg actions: DwarvenMineWorker.Action) {
        workers.forEach { it.action(*actions) }
    }
}