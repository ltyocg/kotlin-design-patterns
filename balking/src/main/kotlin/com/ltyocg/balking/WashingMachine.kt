package com.ltyocg.balking

import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class WashingMachine(
    private val delayProvider: DelayProvider = DelayProvider { interval, timeUnit, task ->
        Thread.sleep(timeUnit.toMillis(interval))
        task()
    }
) {
    private val log = LoggerFactory.getLogger(this::class.java)
    var washingMachineState: WashingMachineState = WashingMachineState.ENABLED
    private val lock = ReentrantLock()

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
        delayProvider.executeAfterDelay(50, TimeUnit.MILLISECONDS) {
            lock.withLock {
                washingMachineState = WashingMachineState.ENABLED
                log.info("{}: Washing completed.", Thread.currentThread().id)
            }
        }
    }
}