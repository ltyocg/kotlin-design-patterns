package com.ltyocg.acyclicvisitor

import com.ltyocg.commons.assertLogContains
import org.slf4j.event.Level
import kotlin.test.Test


class ConfigureForDosVisitorTest {
    @Test
    fun `test visit for zoom`() {
        val zoom = Zoom()
        assertLogContains(Level.INFO, "$zoom used with Dos configurator.") {
            ConfigureForDosVisitor().visit(zoom)
        }
    }

    @Test
    fun `test visit for hayes`() {
        val hayes = Hayes()
        assertLogContains(Level.INFO, "$hayes used with Dos configurator.") {
            ConfigureForDosVisitor().visit(hayes)
        }
    }
}

