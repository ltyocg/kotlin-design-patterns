import org.awaitility.kotlin.await
import org.junit.jupiter.api.assertDoesNotThrow
import java.time.Duration
import kotlin.test.*

class EventAsynchronousTest {
    @Test
    fun `asynchronous event`() {
        val eventManager = EventManager()
        val aEventId = eventManager.createAsync(Duration.ofSeconds(60))
        eventManager.start(aEventId)
        assertEquals(1, eventManager.eventPool.size)
        assertTrue(eventManager.eventPool.size < EventManager.MAX_RUNNING_EVENTS)
        assertEquals(-1, eventManager.numOfCurrentlyRunningSyncEvent())
        eventManager.cancel(aEventId)
        assertTrue(eventManager.eventPool.isEmpty())
    }

    @Test
    fun `synchronous event`() {
        val eventManager = EventManager()
        val sEventId = eventManager.create(Duration.ofSeconds(60))
        eventManager.start(sEventId)
        assertEquals(1, eventManager.eventPool.size)
        assertTrue(eventManager.eventPool.size < EventManager.MAX_RUNNING_EVENTS)
        assertNotEquals(-1, eventManager.numOfCurrentlyRunningSyncEvent())
        eventManager.cancel(sEventId)
        assertTrue(eventManager.eventPool.isEmpty())
    }

    @Test
    fun `full synchronous event`() {
        val eventManager = EventManager()
        val eventTime = Duration.ofSeconds(1)
        val sEventId = eventManager.create(eventTime)
        assertEquals(1, eventManager.eventPool.size)
        eventManager.start(sEventId)
        await.until { eventManager.eventPool.isEmpty() }
    }

    @Test
    fun `unsuccessful synchronous event`() {
        assertFailsWith<InvalidOperationException> {
            val eventManager = EventManager()
            var sEventId = assertDoesNotThrow { eventManager.create(Duration.ofSeconds(60)) }
            eventManager.start(sEventId)
            sEventId = eventManager.create(Duration.ofSeconds(60))
            eventManager.start(sEventId)
        }
    }

    @Test
    fun `full asynchronous event`() {
        val eventManager = EventManager()
        val eventTime = Duration.ofSeconds(1)
        val aEventId1 = eventManager.createAsync(eventTime)
        val aEventId2 = eventManager.createAsync(eventTime)
        val aEventId3 = eventManager.createAsync(eventTime)
        assertEquals(3, eventManager.eventPool.size)
        eventManager.start(aEventId1)
        eventManager.start(aEventId2)
        eventManager.start(aEventId3)
        await.until { eventManager.eventPool.isEmpty() }
    }

    @Test
    fun `long running event exception`() {
        assertFailsWith<LongRunningEventException> {
            EventManager().createAsync(Duration.ofMinutes(31))
        }
    }

    @Test
    fun `max num of events allowed exception`() {
        assertFailsWith<MaxNumOfEventsAllowedException> {
            val eventManager = EventManager()
            for (i in 0..1099) eventManager.createAsync(Duration.ofSeconds(i.toLong()))
        }
    }
}
