import org.slf4j.LoggerFactory
import java.util.concurrent.locks.Lock
import kotlin.concurrent.withLock

class Reader(
    private val name: String,
    private val readLock: Lock,
    private val readingTime: Long = 250L
) : () -> Unit {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun invoke() = readLock.withLock {
        log.info("{} begin", name)
        Thread.sleep(readingTime)
        log.info("{} finish after reading {}ms", name, readingTime)
    }
}