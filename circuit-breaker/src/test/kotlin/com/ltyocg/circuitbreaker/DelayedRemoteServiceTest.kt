package com.ltyocg.circuitbreaker

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DelayedRemoteServiceTest {
    @Test
    fun `test default constructor`() {
        assertFailsWith<RemoteServiceException> { DelayedRemoteService().call() }
    }

    @Test
    fun `test parameterized constructor`() {
        assertEquals("Delayed service is working", DelayedRemoteService(System.nanoTime() - 2000 * 1000 * 1000, 1).call())
    }
}