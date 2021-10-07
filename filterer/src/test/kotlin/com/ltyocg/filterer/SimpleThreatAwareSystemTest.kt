package com.ltyocg.filterer

import kotlin.test.Test
import kotlin.test.assertEquals

class SimpleThreatAwareSystemTest {
    @Test
    fun `should filter by threat type`() {
        val rootkit = SimpleThreat(ThreatType.ROOTKIT, 1, "Simple-Rootkit")
        val rootkitThreatAwareSystem = SimpleThreatAwareSystem("System-1", listOf(rootkit, SimpleThreat(ThreatType.TROJAN, 2, "Simple-Trojan")))
            .filtered().by { it.type() == ThreatType.ROOTKIT }
        assertEquals(1, rootkitThreatAwareSystem.threats().size)
        assertEquals(rootkit, rootkitThreatAwareSystem.threats()[0])
    }
}