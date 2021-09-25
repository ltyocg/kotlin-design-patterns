package com.ltyocg.event.asynchronous

import kotlin.test.*

class EventAsynchronousTest {
    @Test
    fun `test asynchronous event`() {
        with(EventManager()) {
            val aEventId = createAsync(60)
            start(aEventId)
            assertEquals(1, eventPool.size)
            assertTrue(eventPool.size < MAX_RUNNING_EVENTS)
            assertEquals(-1, numOfCurrentlyRunningSyncEvent())
            cancel(aEventId)
            assertTrue(eventPool.isEmpty())
        }
    }

    @Test
    fun `test synchronous event`() {
        with(EventManager()) {
            val sEventId = create(60)
            start(sEventId)
            assertEquals(1, eventPool.size)
            assertTrue(eventPool.size < MAX_RUNNING_EVENTS)
            assertNotEquals(-1, numOfCurrentlyRunningSyncEvent())
            cancel(sEventId)
            assertTrue(eventPool.isEmpty())
        }
    }

    @Test
    fun `test unsuccessful synchronous event`() {
        assertFailsWith<InvalidOperationException> {
            with(EventManager()) {
                start(create(60))
                start(create(60))
            }
        }
    }

    @Test
    fun `test full synchronous event`() {
        with(EventManager()) {
            val eventTime = 1
            val sEventId = create(eventTime)
            assertEquals(1, eventPool.size)
            start(sEventId)
            val endTime = System.currentTimeMillis() + (eventTime + 2 * 1000)
            @Suppress("ControlFlowWithEmptyBody")
            while (System.currentTimeMillis() < endTime);
            assertTrue(eventPool.isEmpty())
        }
    }

    @Test
    fun `test full asynchronous event`() {
        with(EventManager()) {
            val eventTime = 1
            val aEventId1 = createAsync(eventTime)
            val aEventId2 = createAsync(eventTime)
            val aEventId3 = createAsync(eventTime)
            assertEquals(3, eventPool.size)
            start(aEventId1)
            start(aEventId2)
            start(aEventId3)
            val endTime = System.currentTimeMillis() + (eventTime + 2 * 1000)
            @Suppress("ControlFlowWithEmptyBody")
            while (System.currentTimeMillis() < endTime);
            assertTrue(eventPool.isEmpty())
        }
    }
}