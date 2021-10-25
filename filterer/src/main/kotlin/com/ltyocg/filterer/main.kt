package com.ltyocg.filterer

import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")

fun main() {
    filteringSimpleThreats()
    filteringSimpleProbableThreats()
}

private fun filteringSimpleThreats() {
    log.info("### Filtering ThreatAwareSystem by ThreatType ###")
    val threatAwareSystem = SimpleThreatAwareSystem(
        "Sys-1", listOf(
            SimpleThreat(ThreatType.ROOTKIT, 1, "Simple-Rootkit"),
            SimpleThreat(ThreatType.TROJAN, 2, "Simple-Trojan")
        )
    )
    log.info("Filtering ThreatAwareSystem. Initial : {}", threatAwareSystem)
    log.info("Filtered by threatType = ROOTKIT : {}", threatAwareSystem.filtered.by { it.type == ThreatType.ROOTKIT })
}

private fun filteringSimpleProbableThreats() {
    log.info("### Filtering ProbabilisticThreatAwareSystem by probability ###")
    val probabilisticThreatAwareSystem = SimpleProbabilisticThreatAwareSystem(
        "Sys-1", listOf(
            SimpleProbableThreat("Trojan-ArcBomb", 1, ThreatType.TROJAN, 0.99),
            SimpleProbableThreat("Rootkit-Kernel", 2, ThreatType.ROOTKIT, 0.8)
        )
    )
    log.info("Filtering ProbabilisticThreatAwareSystem. Initial : {}", probabilisticThreatAwareSystem)
    log.info("Filtered by probability = 0.99 : {}", probabilisticThreatAwareSystem.filtered.by { it.probability == 0.99 })
}