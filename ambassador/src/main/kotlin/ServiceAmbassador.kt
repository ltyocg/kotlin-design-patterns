import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory
import kotlin.system.measureTimeMillis

class ServiceAmbassador : RemoteServiceInterface {
    private val log = LoggerFactory.getLogger(javaClass)
    override suspend fun doRemoteFunction(value: Int): Long {
        var result = RemoteServiceStatus.FAILURE.remoteServiceStatusValue
        for (i in 1..3) {
            log.info("Time taken (ms): {}", measureTimeMillis { result = RemoteService.doRemoteFunction(value) })
            if (result == RemoteServiceStatus.FAILURE.remoteServiceStatusValue) {
                log.info("Failed to reach remote: ({})", i)
                delay(3000)
            } else break
        }
        return result
    }
}