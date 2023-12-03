import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    filteringSimpleThreats()
    filteringSimpleProbableThreats()
}

private fun filteringSimpleThreats() {
    logger.info { "### Filtering ThreatAwareSystem by ThreatType ###" }
    val threatAwareSystem = SimpleThreatAwareSystem(
        "Sys-1", listOf(
            SimpleThreat(ThreatType.ROOTKIT, 1, "Simple-Rootkit"),
            SimpleThreat(ThreatType.TROJAN, 2, "Simple-Trojan")
        )
    )
    logger.info { "Filtering ThreatAwareSystem. Initial : $threatAwareSystem" }
    logger.info { "Filtered by threatType = ROOTKIT : ${threatAwareSystem.filtered.by { it.type == ThreatType.ROOTKIT }}" }
}

private fun filteringSimpleProbableThreats() {
    logger.info { "### Filtering ProbabilisticThreatAwareSystem by probability ###" }
    val probabilisticThreatAwareSystem = SimpleProbabilisticThreatAwareSystem(
        "Sys-1", listOf(
            SimpleProbableThreat("Trojan-ArcBomb", 1, ThreatType.TROJAN, 0.99),
            SimpleProbableThreat("Rootkit-Kernel", 2, ThreatType.ROOTKIT, 0.8)
        )
    )
    logger.info { "Filtering ProbabilisticThreatAwareSystem. Initial : $probabilisticThreatAwareSystem" }
    logger.info { "Filtered by probability = 0.99 : ${probabilisticThreatAwareSystem.filtered.by { it.probability == 0.99 }}" }
}
