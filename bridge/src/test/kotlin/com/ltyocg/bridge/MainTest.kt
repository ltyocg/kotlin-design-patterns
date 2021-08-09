package com.ltyocg.bridge

import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.Test

class MainTest {
    @Test
    fun `should execute app without exception`() {
        assertDoesNotThrow { main() }
    }
}