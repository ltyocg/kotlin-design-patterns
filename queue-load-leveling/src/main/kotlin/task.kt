import io.github.oshai.kotlinlogging.KotlinLogging

interface Task {
    fun submit(msg: Message)
}

class TaskGenerator(
    private val msgQueue: MessageQueue,
    private val msgCount: Int
) : Task, () -> Unit {
    private val logger = KotlinLogging.logger {}
    override fun submit(msg: Message) = try {
        msgQueue.submitMsg(msg)
    } catch (e: Exception) {
        logger.error { e.message }
    }

    override fun invoke() {
        var count = msgCount
        try {
            while (count > 0) {
                val statusMsg = "Message-$count submitted by ${Thread.currentThread().name}"
                submit(Message(statusMsg))
                logger.info { statusMsg }
                count--
                Thread.sleep(1000)
            }
        } catch (e: Exception) {
            logger.error { e.message }
        }
    }
}
