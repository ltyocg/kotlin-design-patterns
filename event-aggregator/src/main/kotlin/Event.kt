enum class Event(private val description: String) {
    STARK_SIGHTED("Stark sighted"),
    WARSHIPS_APPROACHING("Warships approaching"),
    TRAITOR_DETECTED("Traitor detected");

    override fun toString(): String = description
}