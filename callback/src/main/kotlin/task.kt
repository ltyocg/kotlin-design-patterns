import org.slf4j.LoggerFactory

abstract class Task {
    fun executeWith(callback: () -> Unit) {
        execute()
        callback()
    }

    abstract fun execute()
}

class SimpleTask : Task() {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun execute() = log.info("Perform some important activity and after call the callback method.")
}