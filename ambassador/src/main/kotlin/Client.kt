import org.slf4j.LoggerFactory

class Client {
    private val log = LoggerFactory.getLogger(javaClass)
    suspend fun useService(value: Int): Long =
        ServiceAmbassador.doRemoteFunction(value).also { log.info("Service result: {}", it) }
}