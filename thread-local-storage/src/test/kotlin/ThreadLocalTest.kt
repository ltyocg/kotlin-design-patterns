import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.formattedList
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ThreadLocalTest {
    @Test
    fun `without ThreadLocal`() {
        val assertListAppender = assertListAppender(AbstractThreadLocalExample::class)
        val initialValue = 1234567890
        val threadSize = 2
        val executor = Executors.newFixedThreadPool(threadSize)
        val threadLocal = WithoutThreadLocal(initialValue)
        repeat(threadSize) { executor.submit(threadLocal) }
        executor.awaitTermination(3, TimeUnit.SECONDS)
        assertFalse(assertListAppender.formattedList().all { it.endsWith(initialValue.toString()) })
    }

    @Test
    fun `with ThreadLocal`() {
        val assertListAppender = assertListAppender(AbstractThreadLocalExample::class)
        val initialValue = 1234567890
        val threadSize = 2
        val executor = Executors.newFixedThreadPool(threadSize)
        val threadLocal = WithThreadLocal(ThreadLocal.withInitial { initialValue })
        repeat(threadSize) { executor.submit(threadLocal) }
        executor.awaitTermination(3, TimeUnit.SECONDS)
        threadLocal.remove()
        assertTrue(assertListAppender.formattedList().all { it.endsWith(initialValue.toString()) })
    }
}
