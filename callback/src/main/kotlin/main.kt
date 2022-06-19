import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() = SimpleTask.executeWith { log.info("I'm done now.") }