package com.ltyocg.filterer

interface ThreatAwareSystem {
    fun systemId(): String
    fun threats(): List<Threat>
    fun filtered(): Filterer<out ThreatAwareSystem, out Threat>
}

interface ProbabilisticThreatAwareSystem : ThreatAwareSystem {
    override fun threats(): List<ProbableThreat>
    override fun filtered(): Filterer<out ProbabilisticThreatAwareSystem, out ProbableThreat>
}