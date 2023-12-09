import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.concurrent.Executors
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit

private val logger = KotlinLogging.logger {}
fun main() {
    val executeService = Executors.newFixedThreadPool(10)
    val lock = ReaderWriterLock()
    repeat(5) {
        executeService.submit(Writer("Writer $it", lock.writeLock(), ThreadLocalRandom.current().nextLong(5000)))
    }
    logger.info { "Writers added..." }
    repeat(5) {
        executeService.submit(Reader("Reader $it", lock.readLock(), ThreadLocalRandom.current().nextLong(10)))
    }
    logger.info { "Readers added..." }
    Thread.sleep(5000)
    for (i in 6..9) {
        executeService.submit(Reader("Reader $i", lock.readLock(), ThreadLocalRandom.current().nextLong(10)))
    }
    logger.info { "More readers added..." }
    executeService.shutdown()
    try {
        executeService.awaitTermination(5, TimeUnit.SECONDS)
    } catch (e: Exception) {
        logger.error(e) { "Error waiting for ExecutorService shutdown" }
        Thread.currentThread().interrupt()
    }
}
