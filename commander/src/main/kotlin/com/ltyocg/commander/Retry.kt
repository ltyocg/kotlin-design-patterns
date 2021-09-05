package com.ltyocg.commander

import kotlinx.coroutines.delay
import java.security.SecureRandom
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.min
import kotlin.math.pow

class Retry<T>(
    private val op: Operation,
    private val handleError: HandleErrorIssue<T>,
    private val maxAttempts: Int,
    private val maxDelay: Long,
    vararg ignoreTests: (Exception) -> Boolean
) {
    private val attempts = AtomicInteger()
    private val test = ignoreTests.reduce { acc, predicate -> { acc(it) || predicate(it) } }

    suspend fun perform(list: MutableList<Exception>, obj: T) {
        do {
            try {
                op.operation(list)
                return
            } catch (e: Exception) {
                if (attempts.incrementAndGet() >= maxAttempts || !test(e)) {
                    handleError.handleIssue(obj, e)
                    return
                }
                delay(min((2.0.pow(attempts.toInt()) * 1000 + random.nextInt(1000)).toLong(), maxDelay))
            }
        } while (true)
    }

    companion object {
        private val random = SecureRandom()
    }

    fun interface Operation {
        suspend fun operation(list: MutableList<Exception>)
    }

    fun interface HandleErrorIssue<T> {
        suspend fun handleIssue(obj: T, e: Exception)
    }
}