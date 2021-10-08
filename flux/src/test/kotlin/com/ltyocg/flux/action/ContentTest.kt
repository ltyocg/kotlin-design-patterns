package com.ltyocg.flux.action

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertNotNull

class ContentTest {
    @Test
    fun `test toString`() {
        Content.values().map(Any::toString).forEach {
            assertNotNull(it)
            assertFalse(it.trim().isEmpty())
        }
    }
}