import org.junit.jupiter.api.assertTimeout
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.*
import java.time.Duration
import kotlin.test.*

class ThreadAsyncExecutorTest {
    @Captor
    private lateinit var exceptionCaptor: ArgumentCaptor<Exception>

    @Mock
    private lateinit var task: () -> Any

    @Mock
    private lateinit var callback: AsyncCallback<Any>

    @BeforeTest
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `successful task without callback`() = assertTimeout(Duration.ofMillis(3000)) {
        val result = Any()
        whenever(task()).thenReturn(result)
        val asyncResult = ThreadAsyncExecutor.startProcess(task)
        assertNotNull(asyncResult)
        asyncResult.await()
        assertTrue(asyncResult.isCompleted)
        verify(task, times(1))()
        assertSame(result, asyncResult.value)
    }

    @Test
    fun `successful task with callback`() = assertTimeout(Duration.ofMillis(3000)) {
        val result = Any()
        whenever(task()).thenReturn(result)
        val asyncResult = ThreadAsyncExecutor.startProcess(task, callback)
        assertNotNull(asyncResult)
        asyncResult.await()
        assertTrue(asyncResult.isCompleted)
        verify(task, times(1))()
        verify(callback, times(1)).onComplete(eq(result))
        verify(callback, times(0)).onError(exceptionCaptor.capture())
        assertSame(result, asyncResult.value)
    }

    @Test
    fun `long running task without callback`() {
        assertTimeout(Duration.ofMillis(5000)) {
            val result = Any()
            whenever(task()).thenAnswer {
                Thread.sleep(1500)
                result
            }
            val asyncResult = ThreadAsyncExecutor.startProcess(task)
            assertNotNull(asyncResult)
            assertFalse(asyncResult.isCompleted)
            try {
                asyncResult.value
                fail("Expected IllegalStateException when calling AsyncResult#getValue on a non-completed task")
            } catch (e: IllegalStateException) {
                assertNotNull(e.message)
            }
            verify(task, timeout(3000).times(1))()
            asyncResult.await()
            assertTrue(asyncResult.isCompleted)
            verifyNoMoreInteractions(task)
            assertSame(result, asyncResult.value)
        }
    }

    @Test
    fun `long running task with callback`() {
        assertTimeout(Duration.ofMillis(5000)) {
            val result = Any()
            whenever(task()).thenAnswer {
                Thread.sleep(1500)
                result
            }
            val asyncResult = ThreadAsyncExecutor.startProcess(task, callback)
            assertNotNull(asyncResult)
            assertFalse(asyncResult.isCompleted)
            verifyNoMoreInteractions(callback)
            try {
                asyncResult.value
                fail("Expected IllegalStateException when calling AsyncResult#getValue on a non-completed task")
            } catch (e: IllegalStateException) {
                assertNotNull(e.message)
            }
            verify(task, timeout(3000).times(1))()
            verify(callback, timeout(3000).times(1)).onComplete(eq(result))
            verify(callback, times(0)).onError(isA<Exception>())
            asyncResult.await()
            assertTrue(asyncResult.isCompleted)
            verifyNoMoreInteractions(task, callback)
            assertSame(result, asyncResult.value)
        }
    }

    @Test
    fun endProcess() {
        assertTimeout(Duration.ofMillis(5000)) {
            val result = Any()
            whenever(task()).thenAnswer {
                Thread.sleep(1500)
                result
            }
            val asyncResult = ThreadAsyncExecutor.startProcess(task)
            assertNotNull(asyncResult)
            assertFalse(asyncResult.isCompleted)
            try {
                asyncResult.value
                fail("Expected IllegalStateException when calling AsyncResult#getValue on a non-completed task")
            } catch (e: IllegalStateException) {
                assertNotNull(e.message)
            }
            assertSame(result, ThreadAsyncExecutor.endProcess(asyncResult))
            verify(task, times(1))()
            assertTrue(asyncResult.isCompleted)
            assertSame(result, ThreadAsyncExecutor.endProcess(asyncResult))
            verifyNoMoreInteractions(task)
        }
    }
}
