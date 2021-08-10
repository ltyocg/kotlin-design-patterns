package com.ltyocg.builder

import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.Test

class MainTest {
    @Test
    fun `should execute main without exception`() {
        assertDoesNotThrow { main() }
    }
}