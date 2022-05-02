import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.pow

class RetryExponentialBackoff<T>(
    private val op: BusinessOperation<T>,
    private val maxAttempts: Int,
    private val maxDelay: Long,
    vararg ignoreTests: (Exception) -> Boolean
) : BusinessOperation<T> {
    private val attempts: AtomicInteger = AtomicInteger()
    private val test = ignoreTests.fold({ _: Exception -> false }) { acc, function -> { acc(it) || function(it) } }
    private val errors = mutableListOf<Exception>()
    fun errors(): List<Exception> = errors.toList()
    fun attempts(): Int = attempts.toInt()
    override fun perform(): T {
        while (true) try {
            return op.perform()
        } catch (e: BusinessException) {
            errors.add(e)
            if (attempts.incrementAndGet() >= maxAttempts || !test(e)) throw e
            try {
                Thread.sleep(2.0.pow(attempts().toDouble()).toLong() * 1000 + RANDOM.nextInt(1000).coerceAtMost(maxDelay.toInt()))
            } catch (_: InterruptedException) {
            }
        }
    }

    companion object {
        private val RANDOM = Random()
    }
}