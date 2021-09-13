package com.ltyocg.dirtyflag

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DirtyFlagTest {
    @Test
    fun `test is dirty`() {
        assertFalse(DataFetcher().fetch().isEmpty())
    }

    @Test
    fun `test is not dirty`() {
        assertTrue(DataFetcher().also { it.fetch() }.fetch().isEmpty())
    }
}