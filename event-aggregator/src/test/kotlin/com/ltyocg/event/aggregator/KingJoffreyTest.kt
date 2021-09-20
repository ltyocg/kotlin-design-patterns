package com.ltyocg.event.aggregator

import com.ltyocg.commons.assertLogContains
import org.slf4j.event.Level
import kotlin.test.Test

class KingJoffreyTest {
    @Test
    fun `test onEvent`() {
        val kingJoffrey = KingJoffrey()
        Event.values().forEach {
            assertLogContains(Level.INFO, "Received event from the King's Hand: $it") {
                kingJoffrey.onEvent(it)
            }
        }
    }
}