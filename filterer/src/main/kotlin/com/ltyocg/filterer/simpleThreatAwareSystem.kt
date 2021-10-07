package com.ltyocg.filterer

data class SimpleThreatAwareSystem(
    private val systemId: String,
    private val issues: List<Threat>
) : ThreatAwareSystem {
    override fun systemId(): String = systemId
    override fun threats(): List<Threat> = issues.toList()
    override fun filtered(): Filterer<out ThreatAwareSystem, out Threat> =
        Filterer { SimpleThreatAwareSystem(systemId, issues.filter(it)) }
}

data class SimpleProbabilisticThreatAwareSystem(
    private val systemId: String,
    private val issues: List<ProbableThreat>
) : ProbabilisticThreatAwareSystem {
    override fun systemId(): String = systemId
    override fun threats(): List<ProbableThreat> = issues
    override fun filtered(): Filterer<out ProbabilisticThreatAwareSystem, out ProbableThreat> =
        Filterer { SimpleProbabilisticThreatAwareSystem(systemId, issues.filter(it)) }
}