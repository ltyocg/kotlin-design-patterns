import io.github.oshai.kotlinlogging.KotlinLogging
import java.time.Duration
import java.time.Instant
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean

interface Event {
    fun start()
    fun stop()
    fun status()
}

class AsyncEvent(
    private val eventId: Int,
    private val eventTime: Duration,
    val synchronous: Boolean
) : Event, Runnable {
    private val logger = KotlinLogging.logger {}
    private lateinit var thread: Thread
    private val isComplete = AtomicBoolean(false)
    private lateinit var eventListener: ThreadCompleteListener

    override fun start() {
        thread = Thread(this)
        thread.start()
    }

    override fun stop() {
        thread.interrupt()
    }

    override fun status() = logger.info {
        if (isComplete.get()) "[$eventId] is not done."
        else "[$eventId] is done."
    }

    override fun run() {
        val currentTime = Instant.now()
        val endTime = currentTime.plusSeconds(eventTime.seconds)
        while (Instant.now() < endTime) {
            try {
                TimeUnit.SECONDS.sleep(1)
            } catch (e: InterruptedException) {
                logger.error(e) { "Thread was interrupted: " }
                Thread.currentThread().interrupt()
                return
            }
        }
        isComplete.set(true)
        eventListener.completedEventHandler(eventId)
    }

    fun addListener(listener: ThreadCompleteListener) {
        eventListener = listener
    }
}
