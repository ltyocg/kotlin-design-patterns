package com.ltyocg.hexagonal.domain

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class PlayerDetailsTest {
    @Test
    fun `test equals`() {
        val details1 = PlayerDetails("tom@foo.bar", "11212-123434", "+12323425")
        assertEquals(details1, PlayerDetails("tom@foo.bar", "11212-123434", "+12323425"))
        assertNotEquals(details1, PlayerDetails("john@foo.bar", "16412-123439", "+34323432"))
    }
}