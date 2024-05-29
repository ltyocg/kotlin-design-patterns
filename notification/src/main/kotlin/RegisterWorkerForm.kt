import io.github.oshai.kotlinlogging.KotlinLogging
import java.time.LocalDate

class RegisterWorkerForm(
    private var name: String?,
    private var occupation: String?,
    private var dateOfBirth: LocalDate?
) {
    private val logger = KotlinLogging.logger {}
    private val service = RegisterWorkerService()
    var worker: RegisterWorkerDto? = null
    fun submit() {
        worker = RegisterWorkerDto(name, occupation, dateOfBirth)
        service.registerWorker(worker!!)
        if (worker!!.notification.hasErrors()) {
            worker!!.notification.errors.forEach { logger.error { it } }
            logger.info { "Not registered, see errors" }
        } else logger.info { "Registration Succeeded" }
    }
}
