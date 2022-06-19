package property

enum class Size(private val title: String) {
    SMALL("small"),
    NORMAL("normal"),
    LARGE("large");

    override fun toString(): String = title
}