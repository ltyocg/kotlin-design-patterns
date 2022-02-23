import org.mockito.kotlin.*
import java.io.IOException
import java.util.concurrent.LinkedBlockingQueue
import kotlin.test.Test

class AsynchronousServiceTest {
    private val service = AsynchronousService(LinkedBlockingQueue())
    private val task = mock<AsyncTask<Any>>()

    @Test
    fun `test perfect execution`() {
        val result = Any()
        whenever(task.call()).thenReturn(result)
        service.execute(task)
        verify(task, timeout(2000)).onPostCall(result)
        val inOrder = inOrder(task)
        inOrder.verify(task, times(1)).onPreCall()
        inOrder.verify(task, times(1)).call()
        inOrder.verify(task, times(1)).onPostCall(eq(result))
        verifyNoMoreInteractions(task)
    }

    @Test
    fun `test call exception`() {
        val exception = IOException()
        whenever(task.call()).thenThrow(exception)
        service.execute(task)
        verify(task, timeout(2000)).onError(eq(exception))
        val inOrder = inOrder(task)
        inOrder.verify(task, times(1)).onPreCall()
        inOrder.verify(task, times(1)).call()
        inOrder.verify(task, times(1)).onError(exception)
        verifyNoMoreInteractions(task)
    }
}