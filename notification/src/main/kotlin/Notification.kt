class Notification {
    val errors = mutableListOf<NotificationError>()
    fun hasErrors(): Boolean = errors.isNotEmpty()
    fun addError(error: NotificationError) {
        errors.add(error)
    }
}
