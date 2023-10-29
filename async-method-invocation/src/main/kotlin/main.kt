import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.*

private val logger = KotlinLogging.logger {}
suspend fun main() = withContext(Dispatchers.Default) {
    val deferred1 = lazyVal(10, 500)
    val deferred2 = lazyVal("test", 300)
    val deferred3 = lazyVal(50L, 700)
    val deferred4 = lazyVal(20, 400, "Deploying lunar rover")
    val deferred5 = lazyVal("callback", 600, "Deploying lunar rover")
    delay(350)
    logger.info { "Mission command is sipping coffee" }
    val result1 = deferred1.await()
    val result2 = deferred2.await()
    val result3 = deferred3.await()
    deferred4.await()
    deferred5.await()
    infoLog(result1)
    infoLog(result2)
    infoLog(result3)
}

private fun <T> CoroutineScope.lazyVal(value: T, delayMillis: Long, callback: String? = null): Deferred<T> = async {
    delay(delayMillis)
    infoLog(value)
    value
}.apply {
    if (callback != null) invokeOnCompletion {
        logger.info { "$callback " + if (it == null) "<$value>" else "failed: ${it.localizedMessage}" }
    }
}

private fun infoLog(obj: Any?) = logger.info { "Space rocket <$obj> launched successfully" }
