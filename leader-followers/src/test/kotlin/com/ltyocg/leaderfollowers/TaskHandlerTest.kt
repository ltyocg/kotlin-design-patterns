package com.ltyocg.leaderfollowers

import kotlin.test.Test
import kotlin.test.assertTrue

class TaskHandlerTest {
    @Test
    fun `test handle task`() {
        val handle = Task(100)
        TaskHandler().handleTask(handle)
        assertTrue(handle.finished)
    }
}