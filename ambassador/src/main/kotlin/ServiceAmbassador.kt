import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.delay
import kotlin.system.measureTimeMillis

object ServiceAmbassador : RemoteServiceInterface {
    private val logger = KotlinLogging.logger {}
    override suspend fun doRemoteFunction(value: Int): Long {
        var result = RemoteServiceStatus.FAILURE.remoteServiceStatusValue
        for (i in 1..3) {
            val timeTaken = measureTimeMillis { result = RemoteService.doRemoteFunction(value) }
            logger.info { "Time taken (ms): $timeTaken" }
            if (result == RemoteServiceStatus.FAILURE.remoteServiceStatusValue) {
                logger.info { "Failed to reach remote: ($i)" }
                delay(3000)
            } else break
        }
        return result
    }
}
