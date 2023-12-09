import io.github.oshai.kotlinlogging.KotlinLogging

class Worker(private val task: Task) : () -> Unit {
    private val logger = KotlinLogging.logger {}
    override fun invoke() {
        logger.info { "${Thread.currentThread().name} processing $task" }
        try {
            Thread.sleep(task.timeMs.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
