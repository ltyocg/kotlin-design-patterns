package com.ltyocg.activeobject

import kotlin.test.Test
import kotlin.test.assertEquals

class ActiveCreatureTest {
    @Test
    fun `execution test`() {
        val orc = Orc("orc1")
        assertEquals("orc1", orc.name)
        with(orc) {
            eat()
            roam()
            kill()
        }
    }
}