package com.ltyocg.event.aggregator

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertNotNull

class EventTest {
    @Test
    fun `test toString`() {
        Event.values().asSequence()
            .map(Event::toString)
            .forEach {
                assertNotNull(it)
                assertFalse(it.trim().isEmpty())
            }
    }
}