data class User(
    val firstName: String,
    val lastName: String,
    val active: Boolean,
    val userId: String
)

data class UserDto(
    val firstName: String,
    val lastName: String,
    val active: Boolean,
    val email: String
)