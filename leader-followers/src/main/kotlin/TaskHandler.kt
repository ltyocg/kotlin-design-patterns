import io.github.oshai.kotlinlogging.KotlinLogging

class TaskHandler {
    private val logger = KotlinLogging.logger {}
    fun handleTask(task: Task) {
        Thread.sleep(task.time.toLong())
        logger.info { "It takes ${task.time} milliseconds to finish the task" }
        task.setFinished()
    }
}
