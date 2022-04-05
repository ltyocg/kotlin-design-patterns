import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import java.util.concurrent.BlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class AsynchronousService(workQueue: BlockingQueue<Runnable>) {
    private val coroutineDispatcher = ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, workQueue).asCoroutineDispatcher()
    fun <T> execute(task: AsyncTask<T>) {
        task.catch { task.onPreCall() }
        CoroutineScope(coroutineDispatcher).launch {
            task.catch { task.onPostCall(task()) }
        }
    }

    private fun <T> AsyncTask<T>.catch(block: () -> Unit) = try {
        block()
    } catch (e: Exception) {
        onError(e)
    }

    fun close() = coroutineDispatcher.close()
}