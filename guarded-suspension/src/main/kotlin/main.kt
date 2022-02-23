import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

fun main() {
    Executors.newFixedThreadPool(3).asCoroutineDispatcher().use {
        runBlocking(it) {
            val guardedQueue = GuardedQueue()
            launch { guardedQueue.get() }
            delay(2000)
            launch { guardedQueue.put(20) }
        }
    }
}