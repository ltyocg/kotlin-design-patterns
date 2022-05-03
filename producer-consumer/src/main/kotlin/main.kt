import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import java.util.concurrent.Executors
import kotlin.time.Duration.Companion.seconds

suspend fun main() {
    Executors.newFixedThreadPool(5).asCoroutineDispatcher().use { dispatch ->
        withContext(dispatch) {
            withTimeoutOrNull(10.0.seconds) {
                val queue = Channel<Item>()
                repeat(2) {
                    val producer = Producer("Producer_$it", queue)
                    launch { while (true) producer.produce() }
                }
                repeat(2) {
                    val consumer = Consumer("Consumer_$it", queue)
                    launch { while (true) consumer.consume() }
                }
            }
        }
    }
}