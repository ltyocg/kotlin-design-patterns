package com.ltyocg.leaderelection.bully

import com.ltyocg.leaderelection.BullyMain
import kotlin.test.Test

class BullyMainTest {
    @Test
    fun `should execute main without exception`() {
        BullyMain.main(emptyArray())
    }
}