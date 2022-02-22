import java.security.SecureRandom
import java.util.concurrent.ConcurrentHashMap

interface JobCompleteListener {
    fun completedEventHandler(eventId: Int)
}

const val MAX_RUNNING_EVENTS = 1000
const val MIN_ID = 1
const val MAX_ID = MAX_RUNNING_EVENTS
const val MAX_EVENT_TIME = 1800
private const val DOES_NOT_EXIST = " does not exist."

class EventManager : JobCompleteListener {
    private var currentlyRunningSyncEvent = -1
    private val rand = SecureRandom()
    val eventPool = ConcurrentHashMap<Int, Event>(MAX_RUNNING_EVENTS)
    fun create(eventTime: Int): Int {
        if (currentlyRunningSyncEvent != -1) throw InvalidOperationException("Event [$currentlyRunningSyncEvent] is still running. Please wait until it finishes and try again.")
        return createEvent(eventTime, true).also { currentlyRunningSyncEvent = it }
    }

    fun createAsync(eventTime: Int): Int = createEvent(eventTime, false)
    fun start(eventId: Int) {
        if (!eventPool.containsKey(eventId)) throw EventDoesNotExistException("$eventId$DOES_NOT_EXIST")
        eventPool[eventId]!!.start()
    }

    fun cancel(eventId: Int) {
        if (!eventPool.containsKey(eventId)) throw EventDoesNotExistException("$eventId$DOES_NOT_EXIST")
        if (eventId == currentlyRunningSyncEvent) currentlyRunningSyncEvent = -1
        eventPool[eventId]!!.stop()
        eventPool.remove(eventId)
    }

    fun status(eventId: Int) {
        if (!eventPool.containsKey(eventId)) throw EventDoesNotExistException("$eventId$DOES_NOT_EXIST")
        eventPool[eventId]!!.status()
    }

    fun statusOfAllEvents() {
        eventPool.values.forEach(Event::status)
    }

    fun shutdown() {
        eventPool.values.forEach(Event::stop)
    }

    override fun completedEventHandler(eventId: Int) {
        eventPool[eventId]!!.status()
        if (eventPool[eventId]!!.synchronous) currentlyRunningSyncEvent = -1
        eventPool.remove(eventId)
    }

    fun numOfCurrentlyRunningSyncEvent(): Int = currentlyRunningSyncEvent

    private fun createEvent(eventTime: Int, isSynchronous: Boolean): Int {
        if (eventPool.size == MAX_RUNNING_EVENTS) throw MaxNumOfEventsAllowedException("Too many events are running at the moment. Please try again later.")
        if (eventTime >= MAX_EVENT_TIME) throw LongRunningEventException("Maximum event time allowed is $MAX_EVENT_TIME seconds. Please try again.")
        var newEventId: Int
        do newEventId = rand.nextInt(MAX_ID - MIN_ID + 1) + MIN_ID
        while (eventPool.containsKey(newEventId))
        eventPool[newEventId] = Event(newEventId, eventTime, isSynchronous).also { it.addListener(this) }
        return newEventId
    }
}