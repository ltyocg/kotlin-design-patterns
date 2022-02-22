import org.slf4j.LoggerFactory

class Client {
    private val log = LoggerFactory.getLogger(javaClass)
    private val serviceAmbassador = ServiceAmbassador()
    suspend fun useService(value: Int): Long =
        serviceAmbassador.doRemoteFunction(value)
            .also { log.info("Service result: {}", it) }
}