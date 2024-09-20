import java.util.concurrent.ExecutionException
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

interface AsyncExecutor {
    fun <T> startProcess(task: () -> T?, callback: AsyncCallback<T>? = null): AsyncResult<T>
    fun <T> endProcess(asyncResult: AsyncResult<T>): T?
}

object ThreadAsyncExecutor : AsyncExecutor {
    private const val RUNNING = 1
    private const val FAILED = 2
    private const val COMPLETED = 3
    private val idx = AtomicInteger(0)
    override fun <T> startProcess(task: () -> T?, callback: AsyncCallback<T>?): AsyncResult<T> = CompletableResult(callback).also {
        thread(name = "executor-${idx.incrementAndGet()}") {
            try {
                it.value = task()
            } catch (ex: Exception) {
                it.setException(ex)
            }
        }
    }

    override fun <T> endProcess(asyncResult: AsyncResult<T>): T? {
        if (!asyncResult.isCompleted) asyncResult.await()
        return asyncResult.value
    }

    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
    private class CompletableResult<T>(val callback: AsyncCallback<T>?) : AsyncResult<T> {
        private val lock = Any()

        @Volatile
        private var state = RUNNING
        private lateinit var exception: Exception
        private var _value: T? = null
        override var value: T?
            get() = when (state) {
                COMPLETED -> _value
                FAILED -> throw ExecutionException(exception)
                else -> throw IllegalStateException("Execution not completed yet")
            }
            set(value) {
                _value = value
                state = COMPLETED
                callback?.onComplete(value)
                synchronized(lock, (lock as Object)::notifyAll)
            }

        fun setException(exception: Exception) {
            this.exception = exception
            state = FAILED
            callback?.onError(exception)
            synchronized(lock, (lock as Object)::notifyAll)
        }

        override val isCompleted: Boolean
            get() = state > RUNNING

        override fun await() = synchronized(lock) {
            while (!isCompleted) (lock as Object).wait()
        }
    }
}
