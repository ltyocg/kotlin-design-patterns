import org.slf4j.LoggerFactory

interface Task {
    fun submit(msg: Message)
}

class TaskGenerator(
    private val msgQueue: MessageQueue,
    private val msgCount: Int
) : Task, () -> Unit {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun submit(msg: Message) = try {
        msgQueue.submitMsg(msg)
    } catch (e: Exception) {
        log.error(e.message)
    }

    override fun invoke() {
        var count = msgCount
        try {
            while (count > 0) {
                val statusMsg = "Message-" + count + " submitted by " + Thread.currentThread().name
                submit(Message(statusMsg))
                log.info(statusMsg)
                count--
                Thread.sleep(1000)
            }
        } catch (e: Exception) {
            log.error(e.message)
        }
    }
}