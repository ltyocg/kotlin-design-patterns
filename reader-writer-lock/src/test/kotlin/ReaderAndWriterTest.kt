import com.ltyocg.commons.assertListAppender
import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.test.Test
import kotlin.test.assertContains

class ReaderAndWriterTest {
    private val logger = KotlinLogging.logger {}

    @Test
    fun `read and write`() {
        val lock = ReaderWriterLock()
        with(Executors.newFixedThreadPool(2)) {
            val assertListAppender = assertListAppender(Reader::class, Writer::class)
            submit(Reader("Reader 1", lock.readLock()))
            Thread.sleep(150)
            submit(Writer("Writer 1", lock.writeLock()))
            shutdown()
            try {
                awaitTermination(10, TimeUnit.SECONDS)
            } catch (e: InterruptedException) {
                logger.error(e) { "Error waiting for ExecutorService shutdown" }
            }
            arrayOf(
                "Reader 1 begin",
                "Reader 1 finish",
                "Writer 1 begin",
                "Writer 1 finish"
            ).forEachIndexed { index, s -> assertContains(assertListAppender.list[index].formattedMessage, s) }
        }
    }

    @Test
    fun `write and read`() {
        val lock = ReaderWriterLock()
        with(Executors.newFixedThreadPool(2)) {
            val assertListAppender = assertListAppender(Reader::class, Writer::class)
            submit(Writer("Writer 1", lock.writeLock()))
            Thread.sleep(150)
            submit(Reader("Reader 1", lock.readLock()))
            shutdown()
            try {
                awaitTermination(10, TimeUnit.SECONDS)
            } catch (e: InterruptedException) {
                logger.error(e) { "Error waiting for ExecutorService shutdown" }
            }
            arrayOf(
                "Writer 1 begin",
                "Writer 1 finish",
                "Reader 1 begin",
                "Reader 1 finish"
            ).forEachIndexed { index, s -> assertContains(assertListAppender.list[index].formattedMessage, s) }
        }
    }
}
