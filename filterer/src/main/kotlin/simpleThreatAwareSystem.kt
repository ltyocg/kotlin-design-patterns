data class SimpleThreatAwareSystem(
    override val systemId: String,
    private val issues: List<Threat>
) : ThreatAwareSystem {
    override val threats: List<Threat> = issues.toList()
    override val filtered: Filterer<out ThreatAwareSystem, out Threat> =
        Filterer { SimpleThreatAwareSystem(systemId, issues.filter(it)) }
}

data class SimpleProbabilisticThreatAwareSystem(
    override val systemId: String,
    override val threats: List<ProbableThreat>
) : ProbabilisticThreatAwareSystem {
    override val filtered: Filterer<out ProbabilisticThreatAwareSystem, out ProbableThreat> =
        Filterer { SimpleProbabilisticThreatAwareSystem(systemId, threats.filter(it)) }
}