package com.ltyocg.caching

import kotlin.test.BeforeTest
import kotlin.test.Test

class CachingTest {
    @BeforeTest
    fun setup() {
        AppManager.initDb(false)
        AppManager.initCacheCapacity(3)
    }

    @Test
    fun `test read and write through strategy`() {
        useReadAndWriteThroughStrategy()
    }

    @Test
    fun `test read through and write around strategy`() {
        useReadThroughAndWriteAroundStrategy()
    }

    @Test
    fun `test read through and write behind strategy`() {
        useReadThroughAndWriteBehindStrategy()
    }

    @Test
    fun `test cache aside strategy`() {
        useCacheAsideStrategy()
    }
}