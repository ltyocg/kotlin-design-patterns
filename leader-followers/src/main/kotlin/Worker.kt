import io.github.oshai.kotlinlogging.KotlinLogging

data class Worker(
    val id: Long,
    val workCenter: WorkCenter,
    val taskSet: TaskSet,
    val taskHandler: TaskHandler
) : () -> Unit {
    private val logger = KotlinLogging.logger {}
    override fun invoke() {
        while (!Thread.interrupted()) try {
            if (workCenter.leader != null && workCenter.leader != this) {
                var isContinue = false
                synchronized(workCenter) {
                    if (workCenter.leader != null && workCenter.leader != this) {
                        workCenter.wait()
                        isContinue = true
                    }
                }
                if (isContinue) continue
            }
            val task = taskSet.getTask()
            synchronized(workCenter) {
                workCenter.removeWorker(this)
                workCenter.promoteLeader()
                workCenter.notifyAll()
            }
            taskHandler.handleTask(task)
            logger.info { "The Worker with the ID $id completed the task" }
            workCenter.addWorker(this)
        } catch (e: InterruptedException) {
            logger.warn { "Worker interrupted" }
            Thread.currentThread().interrupt()
            return
        }
    }

    private fun Any.wait() {
        @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
        (this as Object).wait()
    }

    private fun Any.notifyAll() {
        @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
        (this as Object).notifyAll()
    }
}
