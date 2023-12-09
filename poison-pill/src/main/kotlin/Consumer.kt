import io.github.oshai.kotlinlogging.KotlinLogging

class Consumer(private val name: String, private val queue: MqSubscribePoint) {
    private val logger = KotlinLogging.logger {}
    fun consume() {
        while (true) try {
            val msg = queue.take()
            if (Message.POISON_PILL == msg) {
                logger.info { "Consumer $name receive request to terminate." }
                break
            }
            logger.info { "Message [${msg.body}] from [${msg.getHeader(Message.Headers.SENDER)}] received by [$name]" }
        } catch (e: InterruptedException) {
            logger.error(e) { "Exception caught." }
            return
        }
    }
}
