import java.util.*

open class SimpleThreat(
    override val type: ThreatType,
    override val id: Int,
    override val name: String
) : Threat {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as SimpleThreat
        return type == other.type && id == other.id && name == other.name
    }

    override fun hashCode(): Int = Objects.hash(type, id, name)
}

class SimpleProbableThreat(
    name: String,
    id: Int,
    threatType: ThreatType,
    override val probability: Double
) : SimpleThreat(threatType, id, name), ProbableThreat {
    override fun toString(): String = "SimpleProbableThreat(probability=$probability) ${super.toString()}"
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return probability != (other as SimpleProbableThreat).probability
    }

    override fun hashCode(): Int = Objects.hashCode(probability)
}