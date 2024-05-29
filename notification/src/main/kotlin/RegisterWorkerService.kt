class RegisterWorkerService {
    fun registerWorker(registration: RegisterWorkerDto) = RegisterWorker(registration).run()
}
