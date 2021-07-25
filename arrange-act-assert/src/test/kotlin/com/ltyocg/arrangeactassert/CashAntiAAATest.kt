package com.ltyocg.arrangeactassert

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CashAntiAAATest {
    @Test
    fun `test cash`() {
        assertEquals(7, Cash(3).also { it.plus(4) }.count())
        val cash = Cash(8)
        assertTrue(cash.minus(5))
        assertEquals(3, cash.count())
        assertFalse(cash.minus(6))
        assertEquals(3, cash.count())
        cash.plus(5)
        assertTrue(cash.minus(5))
        assertEquals(3, cash.count())
    }
}