import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import kotlin.test.Test

class WorkerTest {
    @Test
    fun invoke() {
        val task = mock<Task>()
        verifyNoMoreInteractions(task)
        Worker(task)()
        verify(task).timeMs
        verifyNoMoreInteractions(task)
    }
}