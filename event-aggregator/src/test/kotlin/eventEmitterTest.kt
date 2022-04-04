import org.mockito.kotlin.*
import java.time.DayOfWeek
import kotlin.test.Test

abstract class EventEmitterTest<E : EventEmitter>(
    private val specialDay: DayOfWeek?,
    private val event: Event?,
    private val factoryWithDefaultObserver: (EventObserver) -> E,
    private val factoryWithoutDefaultObserver: () -> E,
) {
    @Test
    fun `all days`() {
        testAllDaysWithoutDefaultObserver(specialDay, event)
        testAllDaysWithDefaultObserver(specialDay, event)
    }

    private fun testAllDaysWithoutDefaultObserver(specialDay: DayOfWeek?, event: Event?) {
        val observer1 = mock<EventObserver>()
        val observer2 = mock<EventObserver>()
        testAllDays(specialDay, event, factoryWithoutDefaultObserver().apply {
            registerObserver(observer1)
            registerObserver(observer2)
        }, observer1, observer2)
    }

    private fun testAllDaysWithDefaultObserver(specialDay: DayOfWeek?, event: Event?) {
        val defaultObserver = mock<EventObserver>()
        val observer1 = mock<EventObserver>()
        val observer2 = mock<EventObserver>()
        testAllDays(specialDay, event, factoryWithDefaultObserver(defaultObserver).apply {
            registerObserver(observer1)
            registerObserver(observer2)
        }, defaultObserver, observer1, observer2)
    }

    private fun testAllDays(specialDay: DayOfWeek?, event: Event?, emitter: E, vararg observers: EventObserver) {
        DayOfWeek.values().forEach {
            emitter.timePasses(it)
            if (it == specialDay) observers.forEach { observer -> verify(observer, times(1)).onEvent(eq(event)) }
            else verifyNoMoreInteractions(*observers)
        }
        verifyNoMoreInteractions(*observers)
    }
}

class KingsHandTest : EventEmitterTest<KingsHand>(null, null, ::KingsHand, ::KingsHand) {
    @Test
    fun `pass through`() {
        val observer = mock<EventObserver>()
        val kingsHand = KingsHand(observer)
        verifyNoMoreInteractions(observer)
        Event.values().forEach {
            kingsHand.onEvent(it)
            verify(observer, times(1)).onEvent(eq(it))
            verifyNoMoreInteractions(observer)
        }
    }
}

class LordBaelishTest : EventEmitterTest<LordBaelish>(DayOfWeek.FRIDAY, Event.STARK_SIGHTED, ::LordBaelish, ::LordBaelish)
class LordVarysTest : EventEmitterTest<LordVarys>(DayOfWeek.SATURDAY, Event.TRAITOR_DETECTED, ::LordVarys, ::LordVarys)
class ScoutTest : EventEmitterTest<Scout>(DayOfWeek.TUESDAY, Event.WARSHIPS_APPROACHING, ::Scout, ::Scout)