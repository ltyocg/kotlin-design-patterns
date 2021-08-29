package com.ltyocg.balking

import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class WashingMachine {
    private val log = LoggerFactory.getLogger(this::class.java)
    private val delayProvider: DelayProvider
    var washingMachineState: WashingMachineState = WashingMachineState.ENABLED
    private val lock = ReentrantLock()

    constructor(delayProvider: DelayProvider) {
        this.delayProvider = delayProvider
    }

    constructor() {
        delayProvider = DelayProvider { interval, timeUnit, task ->
            Thread.sleep(timeUnit.toMillis(interval))
            task()
        }
    }

    fun wash() {
        lock.withLock {
            log.info("{}: Actual machine state: {}", Thread.currentThread().name, washingMachineState)
            if (washingMachineState == WashingMachineState.WASHING) {
                log.error("Cannot wash if the machine has been already washing!")
                return
            }
            washingMachineState = WashingMachineState.WASHING
        }
        log.info("{}: Doing the washing", Thread.currentThread().name)
        delayProvider.executeAfterDelay(50, TimeUnit.MILLISECONDS, ::endOfWashing)
    }

    fun endOfWashing() = lock.withLock {
        washingMachineState = WashingMachineState.ENABLED
        log.info("{}: Washing completed.", Thread.currentThread().id)
    }
}