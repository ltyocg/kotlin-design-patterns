import io.github.oshai.kotlinlogging.KotlinLogging
import java.security.SecureRandom
import java.util.concurrent.locks.LockSupport

abstract class AbstractThreadLocalExample : Runnable {
    private val logger = KotlinLogging.logger {}
    override fun run() {
        LockSupport.parkNanos(RND.nextInt(RANDOM_THREAD_PARK_START, RANDOM_THREAD_PARK_END).toLong())
        logger.info { "${Thread.currentThread().name}, before value changing: ${getter()()}" }
        setter()(RND.nextInt())
    }

    protected abstract fun setter(): (Int) -> Unit
    protected abstract fun getter(): () -> Int

    companion object {
        private val RND = SecureRandom()
        private const val RANDOM_THREAD_PARK_START = 1000000000
        private const val RANDOM_THREAD_PARK_END = 2000000000
    }
}
