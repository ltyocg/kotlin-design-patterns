package com.ltyocg.event.aggregator

import kotlin.test.Test
import kotlin.test.assertFalse

class EventTest {
    @Test
    fun `test toString`() {
        Event.values().asSequence()
            .map(Event::toString)
            .forEach { assertFalse(it.trim().isEmpty()) }
    }
}