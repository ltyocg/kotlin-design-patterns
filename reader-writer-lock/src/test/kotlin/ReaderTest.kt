import com.ltyocg.commons.assertListAppender
import io.github.oshai.kotlinlogging.KotlinLogging
import org.mockito.kotlin.spy
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.test.Test
import kotlin.test.assertContains

class ReaderTest {
    private val logger = KotlinLogging.logger {}

    @Test
    fun read() {
        val lock = ReaderWriterLock()
        with(Executors.newFixedThreadPool(2)) {
            val assertListAppender = assertListAppender(Reader::class)
            submit(spy(Reader("Reader 1", lock.readLock())))
            Thread.sleep(150)
            submit(spy(Reader("Reader 2", lock.readLock())))
            shutdown()
            try {
                awaitTermination(10, TimeUnit.SECONDS)
            } catch (e: InterruptedException) {
                logger.error(e) { "Error waiting for ExecutorService shutdown" }
            }
            arrayOf(
                "Reader 1 begin",
                "Reader 2 begin",
                "Reader 1 finish",
                "Reader 2 finish"
            ).forEachIndexed { index, s -> assertContains(assertListAppender.list[index].formattedMessage, s) }
        }
    }
}
