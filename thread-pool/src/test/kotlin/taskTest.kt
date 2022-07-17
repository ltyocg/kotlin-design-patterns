import org.junit.jupiter.api.assertTimeout
import java.time.Duration
import java.util.concurrent.Callable
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors
import kotlin.test.Test
import kotlin.test.assertEquals

abstract class TaskTest<T : Task>(
    private val factory: (Int) -> T,
    private val expectedExecutionTime: Int
) {
    private companion object {
        private const val TASK_COUNT = 128 * 1024
        private const val THREAD_COUNT = 8
    }

    @Test
    fun `id generation`() = assertTimeout(Duration.ofMillis(10000)) {
        val service = Executors.newFixedThreadPool(THREAD_COUNT)
        val ids = service.invokeAll((0 until TASK_COUNT).map { Callable { factory(1).id } }).mapNotNull {
            try {
                it.get()
            } catch (e: InterruptedException) {
                null
            } catch (e: ExecutionException) {
                null
            }
        }
        service.shutdownNow()
        assertEquals(TASK_COUNT, ids.size)
        assertEquals(TASK_COUNT, ids.distinct().count())
    }

    @Test
    fun timeMs() = repeat(10) {
        assertEquals(expectedExecutionTime * it, factory(it).timeMs)
    }
}

class CoffeeMakingTaskTest : TaskTest<CoffeeMakingTask>(::CoffeeMakingTask, 100)
class PotatoPeelingTaskTest : TaskTest<PotatoPeelingTask>(::PotatoPeelingTask, 200)