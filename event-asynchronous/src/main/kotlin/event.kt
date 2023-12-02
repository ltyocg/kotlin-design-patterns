import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.*

interface IEvent {
    fun start()
    fun stop()
    fun status()
}

class Event(
    private val eventId: Int,
    private val eventTime: Int,
    val synchronous: Boolean
) : IEvent {
    private val logger = KotlinLogging.logger {}
    private var job: Job? = null
    private var isComplete = false
    private var eventListener: JobCompleteListener? = null
    override fun start() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val endTime = System.currentTimeMillis() + eventTime * 1000
            while (isActive && System.currentTimeMillis() < endTime) delay(1000)
            isComplete = true
            eventListener?.completedEventHandler(eventId)
        }
    }

    override fun stop() {
        job?.cancel()
    }

    override fun status() = logger.info { "[$eventId] is${if (isComplete) "" else " not"} done." }
    fun addListener(listener: JobCompleteListener) {
        eventListener = listener
    }

    fun removeListener() {
        eventListener = null
    }
}
