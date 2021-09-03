package com.ltyocg.commander

import java.security.SecureRandom
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Predicate
import kotlin.math.min
import kotlin.math.pow

class Retry<T>(
    private val op: Operation,
    private val handleError: HandleErrorIssue<T>,
    private val maxAttempts: Int,
    private val maxDelay: Long,
    vararg ignoreTests: Predicate<Exception>
) {
    private val attempts = AtomicInteger()
    private val test = ignoreTests.reduce { acc, predicate -> acc.or(predicate) }
    private val errors = mutableListOf<Exception>()

    fun perform(list: MutableList<Exception>, obj: T) {
        do {
            try {
                op.operation(list)
                return
            } catch (e: Exception) {
                errors.add(e)
                if (attempts.incrementAndGet() >= maxAttempts || !test.test(e)) {
                    handleError.handleIssue(obj, e)
                    return
                }
                Thread.sleep(min((2.0.pow(attempts.toInt()) * 1000 + random.nextInt(1000)).toLong(), maxDelay))
            }
        } while (true)
    }

    companion object {
        private val random = SecureRandom()
    }

    fun interface Operation {
        fun operation(list: MutableList<Exception>)
    }

    fun interface HandleErrorIssue<T> {
        fun handleIssue(obj: T, e: Exception)
    }
}