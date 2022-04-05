import org.mockito.kotlin.*
import java.io.IOException
import java.util.concurrent.LinkedBlockingQueue
import kotlin.test.Test

class AsynchronousServiceTest {
    private val service = AsynchronousService(LinkedBlockingQueue())
    private val task = mock<AsyncTask<Any>>()

    @Test
    fun `perfect execution`() {
        val result = Any()
        whenever(task()).thenReturn(result)
        service.execute(task)
        verify(task, timeout(2000)).onPostCall(result)
        val inOrder = inOrder(task)
        inOrder.verify(task, times(1)).onPreCall()
        inOrder.verify(task, times(1))()
        inOrder.verify(task, times(1)).onPostCall(eq(result))
        verifyNoMoreInteractions(task)
    }

    @Test
    fun `call exception`() {
        val exception = IOException()
        whenever(task()).thenThrow(exception)
        service.execute(task)
        verify(task, timeout(2000)).onError(eq(exception))
        val inOrder = inOrder(task)
        inOrder.verify(task, times(1)).onPreCall()
        inOrder.verify(task, times(1))()
        inOrder.verify(task, times(1)).onError(exception)
        verifyNoMoreInteractions(task)
    }
}