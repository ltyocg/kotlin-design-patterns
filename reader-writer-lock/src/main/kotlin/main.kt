import org.slf4j.LoggerFactory
import java.util.concurrent.Executors
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit

private val log = LoggerFactory.getLogger("main")
fun main() {
    val executeService = Executors.newFixedThreadPool(10)
    val lock = ReaderWriterLock()
    repeat(5) {
        executeService.submit(Writer("Writer $it", lock.writeLock(), ThreadLocalRandom.current().nextLong(5000)))
    }
    log.info("Writers added...")
    repeat(5) {
        executeService.submit(Reader("Reader $it", lock.readLock(), ThreadLocalRandom.current().nextLong(10)))
    }
    log.info("Readers added...")
    Thread.sleep(5000)
    for (i in 6..9) {
        executeService.submit(Reader("Reader $i", lock.readLock(), ThreadLocalRandom.current().nextLong(10)))
    }
    log.info("More readers added...")
    executeService.shutdown()
    try {
        executeService.awaitTermination(5, TimeUnit.SECONDS)
    } catch (e: Exception) {
        log.error("Error waiting for ExecutorService shutdown", e)
        Thread.currentThread().interrupt()
    }
}