package com.ltyocg.callback

import kotlin.test.Test
import kotlin.test.assertEquals

class CallbackTest {
    private var callingCount = 0

    @Test
    fun test() {
        val callback: () -> Unit = { callingCount++ }
        with(SimpleTask()) {
            assertEquals(0, callingCount, "Initial calling count of 0")
            executeWith(callback)
            assertEquals(1, callingCount, "Callback called once")
            executeWith(callback)
            assertEquals(2, callingCount, "Callback called twice")
        }
    }
}