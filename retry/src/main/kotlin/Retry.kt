import java.util.concurrent.atomic.AtomicInteger

class Retry<T>(
    private val op: BusinessOperation<T>,
    private val maxAttempts: Int,
    private val delay: Long,
    vararg ignoreTests: (Exception) -> Boolean
) : BusinessOperation<T> {
    private val attempts = AtomicInteger()
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
                Thread.sleep(delay)
            } catch (_: InterruptedException) {
            }
        }
    }
}