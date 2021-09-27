package com.ltyocg.execute.around

import java.io.File
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class MainTest {
    @Test
    fun `should execute main without exception`() {
        main()
    }

    @BeforeTest
    @AfterTest
    fun cleanup() {
        File("testfile.txt").delete()
    }
}