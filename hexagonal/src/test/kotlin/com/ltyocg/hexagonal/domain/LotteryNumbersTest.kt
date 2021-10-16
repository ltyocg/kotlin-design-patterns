package com.ltyocg.hexagonal.domain

import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class LotteryNumbersTest {
    @Test
    fun `test given numbers`() {
        val numbers = LotteryNumbers.create(setOf(1, 2, 3, 4))
        assertEquals(numbers.numbers.size, 4)
        assertContains(numbers.numbers, 1)
        assertContains(numbers.numbers, 2)
        assertContains(numbers.numbers, 3)
        assertContains(numbers.numbers, 4)
    }

    @Test
    fun `test random numbers`() {
        assertEquals(LotteryNumbers.createRandom().numbers.size, LotteryNumbers.NUM_NUMBERS)
    }

    @Test
    fun `test equals`() {
        val numbers1 = LotteryNumbers.create(setOf(1, 2, 3, 4))
        assertEquals(numbers1, LotteryNumbers.create(setOf(1, 2, 3, 4)))
        assertNotEquals(numbers1, LotteryNumbers.create(setOf(11, 12, 13, 14)))
    }
}