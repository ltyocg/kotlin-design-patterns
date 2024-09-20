import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    val executor = ThreadAsyncExecutor
    val asyncResult1 = executor.startProcess(lazyval(10, 500))
    val asyncResult2 = executor.startProcess(lazyval("test", 300))
    val asyncResult3 = executor.startProcess(lazyval(50L, 700))
    val asyncResult4 = executor.startProcess(
        lazyval(20, 400),
        callback("Deploying lunar rover")
    )
    val asyncResult5 = executor.startProcess(
        lazyval("callback", 600),
        callback("Deploying lunar rover")
    )
    Thread.sleep(350)
    logger.info { "Mission command is sipping coffee" }
    val result1 = executor.endProcess(asyncResult1)
    val result2 = executor.endProcess(asyncResult2)
    val result3 = executor.endProcess(asyncResult3)
    asyncResult4.await()
    asyncResult5.await()
    infoLog(result1)
    infoLog(result2)
    infoLog(result3)
}

private fun <T> lazyval(value: T, delayMillis: Long): () -> T = {
    Thread.sleep(delayMillis)
    infoLog(value)
    value
}

private fun <T> callback(name: String): AsyncCallback<T> = object : AsyncCallback<T> {
    override fun onComplete(value: T?) = logger.info { "$name <$value>" }
    override fun onError(ex: Exception) = logger.info { "$name failed: ${ex.message}" }
}

private fun infoLog(obj: Any?) = logger.info { "Space rocket <$obj> launched successfully" }
