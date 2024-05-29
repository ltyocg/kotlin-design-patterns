import io.github.oshai.kotlinlogging.KotlinLogging
import java.time.LocalDate
import java.time.Period

open class ServerCommand(var data: DataTransferObject) {
    val notification: Notification
        get() = data.notification
}

class RegisterWorker(worker: RegisterWorkerDto) : ServerCommand(worker) {
    private val logger = KotlinLogging.logger {}
    fun run() {
        val ourData = (data as RegisterWorkerDto)
        fail(ourData.name.isNullOrBlank(), RegisterWorkerDto.MISSING_NAME)
        fail(ourData.occupation.isNullOrBlank(), RegisterWorkerDto.MISSING_OCCUPATION)
        ourData.dateOfBirth.let {
            if (it == null) fail(true, RegisterWorkerDto.MISSING_DOB)
            else fail(Period.between(it, LocalDate.now()).years < 18, RegisterWorkerDto.DOB_TOO_SOON)
        }
        if (!notification.hasErrors()) logger.info { "Register worker in backend system" }
    }

    private fun fail(condition: Boolean, error: NotificationError) {
        if (condition) notification.addError(error)
    }
}
