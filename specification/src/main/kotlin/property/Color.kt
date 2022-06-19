package property

enum class Color(private val title: String) {
    DARK("dark"),
    LIGHT("light"),
    GREEN("green"),
    RED("red");

    override fun toString(): String = title
}