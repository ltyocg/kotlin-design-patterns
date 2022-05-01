import org.slf4j.LoggerFactory
import java.util.concurrent.locks.Lock
import kotlin.concurrent.withLock

class Writer(
    private val name: String,
    private val writeLock: Lock,
    private val writingTime: Long = 250L
) : () -> Unit {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun invoke() = writeLock.withLock {
        log.info("{} begin", name)
        Thread.sleep(writingTime)
        log.info("{} finished after writing {}ms", name, writingTime)
    }
}