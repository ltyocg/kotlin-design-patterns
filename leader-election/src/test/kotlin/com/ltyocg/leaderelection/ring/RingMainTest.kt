package com.ltyocg.leaderelection.ring

import com.ltyocg.leaderelection.RingMain
import kotlin.test.Test

class RingMainTest {
    @Test
    fun `should execute main without exception`() {
        RingMain.main(emptyArray())
    }
}