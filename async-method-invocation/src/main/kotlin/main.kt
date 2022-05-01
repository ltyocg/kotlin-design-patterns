import kotlinx.coroutines.*
import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
private const val ROCKET_LAUNCH_LOG_PATTERN = "Space rocket <{}> launched successfully"
suspend fun main() = withContext(Dispatchers.Default) {
    val deferred1 = lazyval(10, 500)
    val deferred2 = lazyval("test", 300)
    val deferred3 = lazyval(50L, 700)
    val deferred4 = lazyval(20, 400, "Deploying lunar rover")
    val deferred5 = lazyval("callback", 600, "Deploying lunar rover")
    delay(350)
    log.info("Mission command is sipping coffee")
    val result1 = deferred1.await()
    val result2 = deferred2.await()
    val result3 = deferred3.await()
    deferred4.await()
    deferred5.await()
    log.info(ROCKET_LAUNCH_LOG_PATTERN, result1)
    log.info(ROCKET_LAUNCH_LOG_PATTERN, result2)
    log.info(ROCKET_LAUNCH_LOG_PATTERN, result3)
}

private fun <T> CoroutineScope.lazyval(value: T, delayMillis: Long, callback: String? = null): Deferred<T> = async {
    delay(delayMillis)
    log.info("Space rocket <{}> launched successfully", value)
    value
}.apply {
    if (callback != null) invokeOnCompletion {
        log.info("$callback " + if (it == null) "<$value>" else "failed: ${it.localizedMessage}")
    }
}
