enum class Size(private val title: String) {
    SMALL("small"), NORMAL("normal");

    override fun toString(): String = title
}

enum class Visibility(private val title: String) {
    VISIBLE("visible"), INVISIBLE("invisible");

    override fun toString(): String = title
}