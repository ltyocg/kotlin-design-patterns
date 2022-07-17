import org.slf4j.LoggerFactory

class Worker(private val task: Task) : () -> Unit {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun invoke() {
        log.info("{} processing {}", Thread.currentThread().name, task.toString())
        try {
            Thread.sleep(task.timeMs.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}