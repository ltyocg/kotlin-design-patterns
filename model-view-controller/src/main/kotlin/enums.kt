enum class Health(private val title: String) {
    HEALTHY("healthy"),
    WOUNDED("wounded"),
    DEAD("dead");

    override fun toString(): String = title
}

enum class Fatigue(private val title: String) {
    ALERT("alert"),
    TIRED("tired"),
    SLEEPING("sleeping");

    override fun toString(): String = title
}

enum class Nourishment(private val title: String) {
    SATURATED("saturated"),
    HUNGRY("hungry"),
    STARVING("starving");

    override fun toString(): String = title
}
