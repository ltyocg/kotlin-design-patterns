import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.concurrent.locks.Lock
import kotlin.concurrent.withLock

class Reader(
    private val name: String,
    private val readLock: Lock,
    private val readingTime: Long = 250L
) : () -> Unit {
    private val logger = KotlinLogging.logger {}
    override fun invoke() = readLock.withLock {
        logger.info { "$name begin" }
        Thread.sleep(readingTime)
        logger.info { "$name finish after reading ${readingTime}ms" }
    }
}
