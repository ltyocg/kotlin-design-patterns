data class User(
    val name: String?,
    val age: Int,
    val sex: Sex,
    val email: String
)

enum class Sex {
    MALE, FEMALE
}