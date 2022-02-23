interface Threat {
    val name: String
    val id: Int
    val type: ThreatType
}

interface ProbableThreat : Threat {
    val probability: Double
}