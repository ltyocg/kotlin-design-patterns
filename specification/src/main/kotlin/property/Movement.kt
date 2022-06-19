package property

enum class Movement(private val title: String) {
    WALKING("walking"),
    SWIMMING("swimming"),
    FLYING("flying");

    override fun toString(): String = title
}