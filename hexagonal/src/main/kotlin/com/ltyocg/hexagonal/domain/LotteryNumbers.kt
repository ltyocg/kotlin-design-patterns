package com.ltyocg.hexagonal.domain

import java.security.SecureRandom
import kotlin.math.min

class LotteryNumbers
private constructor(givenNumbers: Set<Int>) {
    companion object {
        private const val MIN_NUMBER = 1
        private const val MAX_NUMBER = 20
        const val NUM_NUMBERS = 4
        fun createRandom(): LotteryNumbers = LotteryNumbers(RandomNumberGenerator(MIN_NUMBER, MAX_NUMBER).nextSet(NUM_NUMBERS))
        fun create(givenNumbers: Set<Int>): LotteryNumbers = LotteryNumbers(givenNumbers)
    }

    val numbers = givenNumbers.toSet()
    val numbersAsString: String
        get() = numbers.joinToString(",")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return numbers == (other as LotteryNumbers).numbers
    }

    override fun hashCode(): Int = numbers.hashCode()
    private class RandomNumberGenerator(private val min: Int, private val max: Int) {
        private val randomIterator = SecureRandom().ints(0, max - min + 1).iterator()
        fun nextSet(size: Int): Set<Int> {
            val list = (min..max).toMutableList()
            val set = mutableSetOf<Int>()
            val times = min(size, max - min)
            repeat(times) {
                set.add(list.removeAt(randomIterator.nextInt() % (times - it)))
            }
            return set
        }
    }
}