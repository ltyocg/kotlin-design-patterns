package com.ltyocg.balking

import java.util.concurrent.TimeUnit
import kotlin.test.Test
import kotlin.test.assertEquals

class WashingMachineTest {
    @Test
    fun wash() {
        val fakeDelayProvider = FakeDelayProvider()
        val washingMachine = WashingMachine(fakeDelayProvider)
        washingMachine.wash()
        washingMachine.wash()
        val machineStateGlobal = washingMachine.washingMachineState
        fakeDelayProvider.task()
        assertEquals(WashingMachineState.WASHING, machineStateGlobal)
        assertEquals(WashingMachineState.ENABLED, washingMachine.washingMachineState)
    }

    @Test
    fun endOfWashing() {
        val washingMachine = WashingMachine()
        washingMachine.wash()
        assertEquals(WashingMachineState.ENABLED, washingMachine.washingMachineState)
    }

    private class FakeDelayProvider : DelayProvider {
        var task = {}
        override fun executeAfterDelay(interval: Long, timeUnit: TimeUnit, task: () -> Unit) {
            this.task = task
        }
    }
}