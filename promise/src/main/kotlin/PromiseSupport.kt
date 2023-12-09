import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.concurrent.ExecutionException
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

open class PromiseSupport<T> : Future<T> {
    private val logger = KotlinLogging.logger {}
    private val lock = Object()

    @Volatile
    private var state = State.RUNNING

    @Suppress("UNCHECKED_CAST")
    private var value: T = null as T
    private var exception: Exception? = null

    open fun fulfill(value: T) {
        this.value = value
        state = State.COMPLETED
        synchronized(lock) { lock.notifyAll() }
    }

    open fun fulfillExceptionally(exception: Exception?) {
        this.exception = exception
        state = State.FAILED
        synchronized(lock) { lock.notifyAll() }
    }

    override fun cancel(mayInterruptIfRunning: Boolean): Boolean = false
    override fun isCancelled(): Boolean = false
    override fun isDone(): Boolean = state != State.RUNNING
    override fun get(): T {
        synchronized(lock) {
            while (state == State.RUNNING) lock.wait()
        }
        if (state == State.COMPLETED) return value
        throw ExecutionException(exception)
    }

    override fun get(timeout: Long, unit: TimeUnit): T {
        synchronized(lock) {
            while (state == State.RUNNING) try {
                lock.wait(unit.toMillis(timeout))
            } catch (e: InterruptedException) {
                logger.warn(e) { "Interrupted!" }
                Thread.currentThread().interrupt()
            }
        }
        if (state == State.COMPLETED) return value
        throw ExecutionException(exception)
    }

    private enum class State {
        RUNNING, FAILED, COMPLETED
    }
}
