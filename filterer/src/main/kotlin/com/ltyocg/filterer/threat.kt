package com.ltyocg.filterer

interface Threat {
    fun name(): String
    fun id(): Int
    fun type(): ThreatType
}

interface ProbableThreat : Threat {
    fun probability(): Double
}