import com.ltyocg.commons.assertListAppender
import io.github.oshai.kotlinlogging.KotlinLogging
import org.mockito.kotlin.spy
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.test.Test
import kotlin.test.assertContains

class WriterTest {
    private val logger = KotlinLogging.logger {}

    @Test
    fun write() {
        val lock = ReaderWriterLock()
        with(Executors.newFixedThreadPool(2)) {
            val assertListAppender = assertListAppender(Writer::class)
            submit(spy(Writer("Writer 1", lock.writeLock())))
            Thread.sleep(150)
            submit(spy(Writer("Writer 2", lock.writeLock())))
            shutdown()
            try {
                awaitTermination(10, TimeUnit.SECONDS)
            } catch (e: InterruptedException) {
                logger.error(e) { "Error waiting for ExecutorService shutdown" }
            }
            arrayOf(
                "Writer 1 begin",
                "Writer 1 finish",
                "Writer 2 begin",
                "Writer 2 finish"
            ).forEachIndexed { index, s -> assertContains(assertListAppender.list[index].formattedMessage, s) }
        }
    }
}
