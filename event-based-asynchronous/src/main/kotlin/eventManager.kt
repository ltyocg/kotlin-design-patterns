import java.security.SecureRandom
import java.time.Duration
import java.util.concurrent.ConcurrentHashMap

interface ThreadCompleteListener {
    fun completedEventHandler(eventId: Int)
}

private val MAX_EVENT_TIME = Duration.ofSeconds(1800)

class EventManager : ThreadCompleteListener {
    companion object {
        const val MAX_RUNNING_EVENTS = 1000
    }

    private var currentlyRunningSyncEvent = -1
    private val rand = SecureRandom()
    val eventPool = ConcurrentHashMap<Int, AsyncEvent>(MAX_RUNNING_EVENTS)
    fun create(eventTime: Duration): Int =
        if (currentlyRunningSyncEvent != -1) throw InvalidOperationException("Event [$currentlyRunningSyncEvent] is still running. Please wait until it finishes and try again.")
        else createEvent(eventTime, true).also { currentlyRunningSyncEvent = it }

    fun createAsync(eventTime: Duration): Int = createEvent(eventTime, false)
    private fun createEvent(eventTime: Duration, isSynchronous: Boolean): Int {
        require(!eventTime.isNegative) { "eventTime cannot be negative" }
        if (eventPool.size == MAX_RUNNING_EVENTS) throw MaxNumOfEventsAllowedException("Too many events are running at the moment. Please try again later.")
        if (eventTime.seconds > MAX_EVENT_TIME.seconds) throw LongRunningEventException("Maximum event time allowed is $MAX_EVENT_TIME seconds. Please try again.")
        val newEventId = generateId()
        AsyncEvent(newEventId, eventTime, isSynchronous).also {
            it.addListener(this)
            eventPool[newEventId] = it
        }
        return newEventId
    }

    fun start(eventId: Int) {
        eventPool.checkContains(eventId)
        eventPool[eventId]!!.start()
    }

    fun cancel(eventId: Int) {
        eventPool.checkContains(eventId)
        if (eventId == currentlyRunningSyncEvent) currentlyRunningSyncEvent = -1
        eventPool[eventId]!!.stop()
        eventPool.remove(eventId)
    }

    fun status(eventId: Int) {
        eventPool.checkContains(eventId)
        eventPool[eventId]!!.status()
    }

    fun statusOfAllEvents() = eventPool.values.forEach { it.status() }
    fun shutdown() = eventPool.values.forEach { it.stop() }
    private fun generateId(): Int {
        var randomNum: Int
        do randomNum = rand.nextInt(MAX_RUNNING_EVENTS) + 1
        while (eventPool.containsKey(randomNum))
        return randomNum
    }

    override fun completedEventHandler(eventId: Int) {
        eventPool[eventId]!!.status()
        if (eventPool[eventId]!!.synchronous) currentlyRunningSyncEvent = -1
        eventPool.remove(eventId)
    }

    fun numOfCurrentlyRunningSyncEvent(): Int {
        return currentlyRunningSyncEvent
    }

    private fun ConcurrentHashMap<Int, AsyncEvent>.checkContains(eventId: Int) {
        if (!containsKey(eventId)) throw EventDoesNotExistException("$eventId does not exist.")
    }
}
