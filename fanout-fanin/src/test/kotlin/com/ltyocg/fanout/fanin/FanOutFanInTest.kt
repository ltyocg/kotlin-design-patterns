package com.ltyocg.fanout.fanin

import kotlin.test.Test
import kotlin.test.assertEquals

class FanOutFanInTest {
    @Test
    fun `fanOutFanIn test`() {
        assertEquals(139L, FanOutFanIn.fanOutFanIn(listOf(1L, 3L, 4L, 7L, 8L).map(::SquareNumberRequest), Consumer(0L)))
    }
}