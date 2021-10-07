package com.ltyocg.filterer

import java.util.*

open class SimpleThreat(
    private val threatType: ThreatType,
    private val id: Int,
    private val name: String
) : Threat {
    override fun name(): String = name
    override fun id(): Int = id
    override fun type(): ThreatType = threatType
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as SimpleThreat
        return threatType == other.threatType && id == other.id && name == other.name
    }

    override fun hashCode(): Int = Objects.hash(threatType, id, name)
}

class SimpleProbableThreat(
    name: String,
    id: Int,
    threatType: ThreatType,
    private val probability: Double
) : SimpleThreat(threatType, id, name), ProbableThreat {
    override fun probability(): Double = probability
    override fun toString(): String = "SimpleProbableThreat(probability=$probability) ${super.toString()}"
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return probability != (other as SimpleProbableThreat).probability
    }

    override fun hashCode(): Int = Objects.hashCode(probability)
}