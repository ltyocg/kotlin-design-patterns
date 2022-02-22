import kotlinx.coroutines.*
import org.slf4j.LoggerFactory

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
    private val log = LoggerFactory.getLogger(javaClass)
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

    override fun status() {
        log.info("[{}] is{} done.", eventId, if (isComplete) "" else " not")
    }

    fun addListener(listener: JobCompleteListener) {
        eventListener = listener
    }

    fun removeListener() {
        eventListener = null
    }
}