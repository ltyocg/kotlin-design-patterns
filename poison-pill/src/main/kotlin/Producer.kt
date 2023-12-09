import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.*

class Producer(private val name: String, private val queue: MqPublishPoint) {
    private val logger = KotlinLogging.logger {}
    private var isStopped = false
    fun send(body: String?) {
        check(!isStopped) { "Producer $body was stopped and fail to deliver requested message [$name]." }
        try {
            queue.put(SimpleMessage().apply {
                addHeader(Message.Headers.DATE, Date().toString())
                addHeader(Message.Headers.SENDER, name)
                this.body = body
            })
        } catch (e: InterruptedException) {
            logger.error(e) { "Exception caught." }
        }
    }

    fun stop() {
        isStopped = true
        try {
            queue.put(Message.POISON_PILL)
        } catch (e: InterruptedException) {
            logger.error(e) { "Exception caught." }
        }
    }
}
