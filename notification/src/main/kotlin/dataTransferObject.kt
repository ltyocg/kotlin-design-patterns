import java.time.LocalDate

open class DataTransferObject {
    val notification = Notification()
}

data class RegisterWorkerDto(
    val name: String? = null,
    val occupation: String? = null,
    val dateOfBirth: LocalDate? = null
) : DataTransferObject() {
    companion object {
        val MISSING_NAME: NotificationError = NotificationError(1, "Name is missing")
        val MISSING_OCCUPATION: NotificationError = NotificationError(2, "Occupation is missing")
        val MISSING_DOB: NotificationError = NotificationError(3, "Date of birth is missing")
        val DOB_TOO_SOON: NotificationError = NotificationError(4, "Worker registered must be over 18")
    }
}
