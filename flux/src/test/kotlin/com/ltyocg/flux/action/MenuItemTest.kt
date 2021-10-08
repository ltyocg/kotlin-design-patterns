package com.ltyocg.flux.action

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertNotNull

class MenuItemTest {
    @Test
    fun `test toString`() {
        MenuItem.values().map(Any::toString).forEach {
            assertNotNull(it)
            assertFalse(it.trim().isEmpty())
        }
    }
}