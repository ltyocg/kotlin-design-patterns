import java.time.DayOfWeek
import java.util.*

abstract class EventEmitter() {
    private val observers: MutableList<EventObserver> = LinkedList()

    constructor(obs: EventObserver) : this() {
        registerObserver(obs)
    }

    fun registerObserver(obs: EventObserver) {
        observers.add(obs)
    }

    protected fun notifyObservers(e: Event?) = observers.forEach { it.onEvent(e) }
    abstract fun timePasses(day: DayOfWeek)
}

class KingsHand : EventEmitter, EventObserver {
    constructor() : super()
    constructor(obs: EventObserver) : super(obs)

    override fun onEvent(e: Event?) = notifyObservers(e)
    override fun timePasses(day: DayOfWeek) {}
}

class LordBaelish : EventEmitter {
    constructor() : super()
    constructor(obs: EventObserver) : super(obs)

    override fun timePasses(day: DayOfWeek) {
        if (day == DayOfWeek.FRIDAY) notifyObservers(Event.STARK_SIGHTED)
    }
}

class LordVarys : EventEmitter {
    constructor() : super()
    constructor(obs: EventObserver) : super(obs)

    override fun timePasses(day: DayOfWeek) {
        if (day == DayOfWeek.SATURDAY) notifyObservers(Event.TRAITOR_DETECTED)
    }
}

class Scout : EventEmitter {
    constructor() : super()
    constructor(obs: EventObserver) : super(obs)

    override fun timePasses(day: DayOfWeek) {
        if (day == DayOfWeek.TUESDAY) notifyObservers(Event.WARSHIPS_APPROACHING)
    }
}