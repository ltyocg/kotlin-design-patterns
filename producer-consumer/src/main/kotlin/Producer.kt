import java.security.SecureRandom

class Producer(private val name: String, private val queue: ItemQueue) {
    private var itemId = 0
    fun produce() {
        queue.put(Item(name, itemId++))
        Thread.sleep(RANDOM.nextInt(2000).toLong())
    }

    companion object {
        private val RANDOM = SecureRandom()
    }
}