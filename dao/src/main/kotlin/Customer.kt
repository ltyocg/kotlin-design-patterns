data class Customer(
    var id: Int,
    var firstName: String,
    var lastName: String
) {
    override fun equals(other: Any?): Boolean = this === other || other is Customer && id == other.id
    override fun hashCode(): Int = id
}
