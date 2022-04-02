import java.util.concurrent.ArrayBlockingQueue

interface MqPublishPoint {
    fun put(msg: Message)
}

interface MqSubscribePoint {
    fun take(): Message
}

class SimpleMessageQueue(bound: Int) : MqPublishPoint, MqSubscribePoint {
    private val queue = ArrayBlockingQueue<Message>(bound)
    override fun put(msg: Message) = queue.put(msg)
    override fun take(): Message = queue.take()
}