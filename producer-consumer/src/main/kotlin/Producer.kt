import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import java.security.SecureRandom

class Producer(private val name: String, private val queue: Channel<Item>) {
    private var itemId = 0
    suspend fun produce() {
        queue.send(Item(name, itemId++))
        delay(RANDOM.nextInt(2000).toLong())
    }

    private companion object {
        private val RANDOM = SecureRandom()
    }
}