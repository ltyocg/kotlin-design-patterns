package com.ltyocg.dependency.injection

import com.ltyocg.commons.assertLogContains
import org.slf4j.event.Level
import kotlin.test.Test

class AdvancedWizardTest {
    @Test
    fun `test smoke everything`() {
        listOf(OldTobyTobacco(), RivendellTobacco(), SecondBreakfastTobacco()).forEach {
            assertLogContains(Level.INFO, "AdvancedWizard smoking ${it::class.simpleName}") {
                AdvancedWizard(it).smoke()
            }
        }
    }
}