import org.slf4j.LoggerFactory

class TaskHandler {
    private val log = LoggerFactory.getLogger(javaClass)
    fun handleTask(task: Task) {
        Thread.sleep(task.time.toLong())
        log.info("It takes {} milliseconds to finish the task", task.time)
        task.setFinished()
    }
}