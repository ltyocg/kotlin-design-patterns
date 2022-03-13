enum class Action(private val title: String, val description: String) {
    HUNT("hunted a rabbit", "arrives for dinner"),
    TALE("tells a tale", "comes to listen"),
    GOLD("found gold", "takes his share of the gold"),
    ENEMY("spotted enemies", "runs for cover"),
    NONE("", "");

    override fun toString(): String = title
}
