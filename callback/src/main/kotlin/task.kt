import io.github.oshai.kotlinlogging.KotlinLogging

abstract class Task {
    fun executeWith(callback: () -> Unit) {
        execute()
        callback()
    }

    abstract fun execute()
}

object SimpleTask : Task() {
    private val logger = KotlinLogging.logger {}
    override fun execute() = logger.info { "Perform some important activity and after call the callback method." }
}
