import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

suspend fun main() {
    Executors.newFixedThreadPool(3).asCoroutineDispatcher().use {
        withContext(it) {
            val guardedQueue = GuardedQueue()
            launch { guardedQueue.get() }
            delay(2000)
            launch { guardedQueue.put(20) }
        }
    }
}