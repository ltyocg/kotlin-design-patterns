import com.ltyocg.commons.logContents
import org.slf4j.LoggerFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.test.Test
import kotlin.test.assertTrue

class ReaderAndWriterTest {
    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun `read and write`() {
        val lock = ReaderWriterLock()
        with(Executors.newFixedThreadPool(2)) {
            val logMessageList = logContents {
                submit(wrap(Reader("Reader 1", lock.readLock())))
                Thread.sleep(150)
                submit(wrap(Writer("Writer 1", lock.writeLock())))
                shutdown()
                try {
                    awaitTermination(10, TimeUnit.SECONDS)
                } catch (e: InterruptedException) {
                    log.error("Error waiting for ExecutorService shutdown", e)
                }
            }
            assertTrue(listOf(
                "Reader 1 begin",
                "Reader 1 finish",
                "Writer 1 begin",
                "Writer 1 finish"
            ).all { logMessageList.any { l -> it in l } })
        }
    }

    @Test
    fun `write and read`() {
        val lock = ReaderWriterLock()
        with(Executors.newFixedThreadPool(2)) {
            val logMessageList = logContents {
                submit(wrap(Writer("Writer 1", lock.writeLock())))
                Thread.sleep(150)
                submit(wrap(Reader("Reader 1", lock.readLock())))
                shutdown()
                try {
                    awaitTermination(10, TimeUnit.SECONDS)
                } catch (e: InterruptedException) {
                    log.error("Error waiting for ExecutorService shutdown", e)
                }
            }
            assertTrue(listOf(
                "Writer 1 begin",
                "Writer 1 finish",
                "Reader 1 begin",
                "Reader 1 finish"
            ).all { logMessageList.any { l -> it in l } })
        }
    }
}