package com.ltyocg.guarded.suspension

import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors
import kotlin.properties.Delegates
import kotlin.test.Test
import kotlin.test.assertEquals

class GuardedQueueTest {
    private var value by Delegates.notNull<Int>()

    @Test
    fun `test get`() {
        Executors.newFixedThreadPool(2).asCoroutineDispatcher().use {
            runBlocking(it) {
                val g = GuardedQueue()
                launch { value = g.get() }
                launch { g.put(10) }
            }
        }
        assertEquals(10, value)
    }

    @Test
    fun `test put`() = runBlocking {
        val g = GuardedQueue()
        g.put(12)
        assertEquals(12, g.get())
    }
}