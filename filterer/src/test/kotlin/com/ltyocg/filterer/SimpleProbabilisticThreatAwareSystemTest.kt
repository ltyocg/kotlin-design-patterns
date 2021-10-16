package com.ltyocg.filterer

import kotlin.test.Test
import kotlin.test.assertEquals

class SimpleProbabilisticThreatAwareSystemTest {
    @Test
    fun `should filter by probability`() {
        val trojan = SimpleProbableThreat("Trojan-ArcBomb", 1, ThreatType.TROJAN, 0.99)
        val filtered = SimpleProbabilisticThreatAwareSystem("System-1", listOf(trojan, SimpleProbableThreat("Rootkit-Kernel", 2, ThreatType.ROOTKIT, 0.8)))
            .filtered.by { it.probability == 0.99 }
        assertEquals(1, filtered.threats.size)
        assertEquals(trojan, filtered.threats[0])
    }
}