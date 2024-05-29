class NotificationError(
    private val errorId: Int,
    private val errorMessage: String
) {
    override fun toString(): String = "Error $errorId: $errorMessage"
}
