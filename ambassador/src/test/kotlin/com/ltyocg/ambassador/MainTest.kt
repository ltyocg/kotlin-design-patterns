package com.ltyocg.ambassador

import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.Test

class MainTest {
    @Test
    fun `should execute application without exception`() {
        assertDoesNotThrow { main() }
    }
}