import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.test.*

class PromiseTest {
    private lateinit var executor: Executor
    private lateinit var promise: Promise<Int>

    @BeforeTest
    fun setUp() {
        executor = Executors.newSingleThreadExecutor()
        promise = Promise()
    }

    @Test
    fun `promise is fulfilled with the resultant value of executing the task`() {
        promise.fulfillInAsync(executor, NumberCrunchingTask())
        assertEquals(NumberCrunchingTask.CRUNCHED_NUMBER, promise.get())
        assertTrue(promise.isDone)
        assertFalse(promise.isCancelled)
    }

    @Test
    fun `promise is fulfilled with an exception if task throws an exception`() {
        testWaitingForeverForPromiseToBeFulfilled()
        testWaitingSomeTimeForPromiseToBeFulfilled()
    }

    private fun testWaitingForeverForPromiseToBeFulfilled() {
        val promise = Promise<Int>().fulfillInAsync(executor) { throw RuntimeException("Barf!") }
        try {
            promise.get()
            fail("Fetching promise should result in exception if the task threw an exception")
        } catch (e: ExecutionException) {
            assertTrue(promise.isDone)
            assertFalse(promise.isCancelled)
        }
        try {
            promise[1000, TimeUnit.SECONDS]
            fail("Fetching promise should result in exception if the task threw an exception")
        } catch (e: ExecutionException) {
            assertTrue(promise.isDone)
            assertFalse(promise.isCancelled)
        }
    }

    private fun testWaitingSomeTimeForPromiseToBeFulfilled() {
        val promise = Promise<Int>().fulfillInAsync(executor) { throw RuntimeException("Barf!") }
        try {
            promise[1000, TimeUnit.SECONDS]
            fail("Fetching promise should result in exception if the task threw an exception")
        } catch (e: ExecutionException) {
            assertTrue(promise.isDone)
            assertFalse(promise.isCancelled)
        }
        try {
            promise.get()
            fail("Fetching promise should result in exception if the task threw an exception")
        } catch (e: ExecutionException) {
            assertTrue(promise.isDone)
            assertFalse(promise.isCancelled)
        }
    }

    @Test
    fun `dependent promise is fulfilled after the consumer consumes the result of this promise`() {
        val dependentPromise = promise
            .fulfillInAsync(executor, NumberCrunchingTask())
            .thenAccept { assertEquals(NumberCrunchingTask.CRUNCHED_NUMBER, it) }
        dependentPromise.get()
        assertTrue(dependentPromise.isDone)
        assertFalse(dependentPromise.isCancelled)
    }

    @Test
    fun `dependent promise is fulfilled with an exception if consumer throws an exception`() {
        val dependentPromise = promise
            .fulfillInAsync(executor, NumberCrunchingTask())
            .thenAccept { throw RuntimeException("Barf!") }
        try {
            dependentPromise.get()
            fail("Fetching dependent promise should result in exception if the action threw an exception")
        } catch (e: ExecutionException) {
            assertTrue(promise.isDone)
            assertFalse(promise.isCancelled)
        }
        try {
            dependentPromise[1000, TimeUnit.SECONDS]
            fail("Fetching dependent promise should result in exception if the action threw an exception")
        } catch (e: ExecutionException) {
            assertTrue(promise.isDone)
            assertFalse(promise.isCancelled)
        }
    }

    @Test
    fun `dependent promise is fulfilled after the function transforms the result of this promise`() {
        val dependentPromise = promise
            .fulfillInAsync(executor, NumberCrunchingTask())
            .thenApply {
                assertEquals(NumberCrunchingTask.CRUNCHED_NUMBER, it)
                it.toString()
            }
        assertEquals(NumberCrunchingTask.CRUNCHED_NUMBER.toString(), dependentPromise.get())
        assertTrue(dependentPromise.isDone)
        assertFalse(dependentPromise.isCancelled)
    }

    @Test
    fun `dependent promise is fulfilled with an exception if the function throws exception`() {
        val dependentPromise = promise
            .fulfillInAsync(executor, NumberCrunchingTask())
            .thenApply<Any> { throw RuntimeException("Barf!") }
        try {
            dependentPromise.get()
            fail("Fetching dependent promise should result in exception if the function threw an exception")
        } catch (e: ExecutionException) {
            assertTrue(promise.isDone)
            assertFalse(promise.isCancelled)
        }
        try {
            dependentPromise[1000, TimeUnit.SECONDS]
            fail("Fetching dependent promise should result in exception if the function threw an exception")
        } catch (e: ExecutionException) {
            assertTrue(promise.isDone)
            assertFalse(promise.isCancelled)
        }
    }

    @Test
    fun `fetching an already fulfilled promise returns the fulfilled value immediately`() = assertEquals(
        NumberCrunchingTask.CRUNCHED_NUMBER,
        Promise<Int>().apply {
            fulfill(NumberCrunchingTask.CRUNCHED_NUMBER)
        }[1000, TimeUnit.SECONDS]
    )

    @Test
    fun `exception handler is called when promise is fulfilled exceptionally`() {
        val exceptionHandler = mock<(Throwable?) -> Unit>()
        val promise = Promise<Any>().onError(exceptionHandler)
        val exception = Exception("barf!")
        promise.fulfillExceptionally(exception)
        verify(exceptionHandler)(eq(exception))
    }

    private class NumberCrunchingTask : () -> Int {
        override fun invoke(): Int {
            Thread.sleep(100)
            return CRUNCHED_NUMBER
        }

        companion object {
            const val CRUNCHED_NUMBER = Int.MAX_VALUE
        }
    }
}