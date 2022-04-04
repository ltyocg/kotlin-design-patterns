import java.util.concurrent.LinkedBlockingQueue

class ItemQueue {
    private val queue = LinkedBlockingQueue<Item>(5)
    fun put(item: Item) = queue.put(item)
    fun take(): Item = queue.take()
}