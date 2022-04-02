import org.slf4j.LoggerFactory

class Consumer(private val name: String, private val queue: MqSubscribePoint) {
    private val log = LoggerFactory.getLogger(javaClass)
    fun consume() {
        while (true) try {
            val msg = queue.take()
            if (Message.POISON_PILL == msg) {
                log.info("Consumer {} receive request to terminate.", name)
                break
            }
            log.info("Message [{}] from [{}] received by [{}]", msg.body, msg.getHeader(Message.Headers.SENDER), name)
        } catch (e: InterruptedException) {
            log.error("Exception caught.", e)
            return
        }
    }
}