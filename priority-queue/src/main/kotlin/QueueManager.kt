import java.util.concurrent.PriorityBlockingQueue

class QueueManager(initialCapacity: Int) {
    private val messagePriorityMessageQueue = PriorityBlockingQueue<Message>(initialCapacity, Comparator.reverseOrder())
    fun publishMessage(message: Message) = messagePriorityMessageQueue.add(message)
    fun receiveMessage(): Message? =
        if (messagePriorityMessageQueue.isEmpty()) null
        else messagePriorityMessageQueue.remove()
}