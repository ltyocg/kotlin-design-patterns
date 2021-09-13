package com.ltyocg.dependency.injection

import com.ltyocg.commons.assertLogContains
import org.slf4j.event.Level
import kotlin.test.Test

class AdvancedSorceressTest {
    @Test
    fun `test smoke everything`() {
        listOf(OldTobyTobacco(), RivendellTobacco(), SecondBreakfastTobacco()).forEach {
            assertLogContains(Level.INFO, "AdvancedSorceress smoking ${it::class.simpleName}") {
                AdvancedSorceress().apply { setTobacco(it) }.smoke()
            }
        }
    }
}