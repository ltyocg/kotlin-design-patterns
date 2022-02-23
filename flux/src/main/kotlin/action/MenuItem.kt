package action

enum class MenuItem(private val title: String) {
    HOME("Home"), PRODUCTS("Products"), COMPANY("Company");

    override fun toString(): String = title
}