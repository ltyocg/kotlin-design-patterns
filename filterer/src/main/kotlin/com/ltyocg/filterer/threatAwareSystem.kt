package com.ltyocg.filterer

interface ThreatAwareSystem {
    val systemId: String
    val threats: List<Threat>
    val filtered: Filterer<out ThreatAwareSystem, out Threat>
}

interface ProbabilisticThreatAwareSystem : ThreatAwareSystem {
    override val threats: List<ProbableThreat>
    override val filtered: Filterer<out ProbabilisticThreatAwareSystem, out ProbableThreat>
}