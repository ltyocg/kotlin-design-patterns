import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

private val logger = KotlinLogging.logger {}
fun main() {
    val inventory = Inventory(1000)
    val coroutineDispatcher = Executors.newFixedThreadPool(3).asCoroutineDispatcher()
    runBlocking(coroutineDispatcher) {
        try {
            withTimeout(TimeUnit.SECONDS.toMillis(5)) {
                repeat(3) {
                    launch {
                        while (inventory.addItem(Item())) logger.info { "Adding another item" }
                    }
                }
            }
        } catch (e: TimeoutCancellationException) {
            logger.error { "Error waiting for ExecutorService shutdown" }
        }
        coroutineDispatcher.close()
    }
}
