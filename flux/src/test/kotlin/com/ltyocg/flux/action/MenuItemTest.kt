package com.ltyocg.flux.action

import kotlin.test.Test
import kotlin.test.assertFalse

class MenuItemTest {
    @Test
    fun `test toString`() {
        MenuItem.values().map(Any::toString).forEach {
            assertFalse(it.trim().isEmpty())
        }
    }
}