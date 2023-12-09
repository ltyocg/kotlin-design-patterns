import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.concurrent.locks.Lock
import kotlin.concurrent.withLock

class Writer(
    private val name: String,
    private val writeLock: Lock,
    private val writingTime: Long = 250L
) : () -> Unit {
    private val logger = KotlinLogging.logger {}
    override fun invoke() = writeLock.withLock {
        logger.info { "$name begin" }
        Thread.sleep(writingTime)
        logger.info { "$name finished after writing ${writingTime}ms" }
    }
}
