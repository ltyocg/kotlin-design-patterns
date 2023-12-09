import io.github.oshai.kotlinlogging.KotlinLogging
import kotlin.reflect.full.isSuperclassOf

private val logger = KotlinLogging.logger {}
private const val NOT_FOUND = "not found"
private lateinit var op: BusinessOperation<String>
fun main() {
    noErrors()
    errorNoRetry()
    errorWithRetry()
    errorWithRetryExponentialBackoff()
}

private fun noErrors() {
    op = FindCustomer("123")
    op.perform()
    logger.info { "Sometimes the operation executes with no errors." }
}

private fun errorNoRetry() {
    op = FindCustomer("123", CustomerNotFoundException(NOT_FOUND))
    try {
        op.perform()
    } catch (e: CustomerNotFoundException) {
        logger.info { "Yet the operation will throw an error every once in a while." }
    }
}

private fun errorWithRetry() {
    val retry = Retry(
        FindCustomer("123", CustomerNotFoundException(NOT_FOUND)),
        3,
        100,
        { CustomerNotFoundException::class.isSuperclassOf(it::class) }
    )
    op = retry
    logger.info { "However, retrying the operation while ignoring a recoverable error will eventually yield the result ${op.perform()} after a number of attempts ${retry.attempts()}" }
}

private fun errorWithRetryExponentialBackoff() {
    val retry = RetryExponentialBackoff(
        FindCustomer("123", CustomerNotFoundException(NOT_FOUND)),
        6,
        30000,
        { CustomerNotFoundException::class.isSuperclassOf(it::class) }
    )
    op = retry
    logger.info { "However, retrying the operation while ignoring a recoverable error will eventually yield the result ${op.perform()} after a number of attempts ${retry.attempts()}" }
}
