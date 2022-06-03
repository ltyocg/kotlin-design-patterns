import org.slf4j.LoggerFactory

object InitContext {
    private val log = LoggerFactory.getLogger(javaClass)
    fun lookup(serviceName: String): Any? = when (serviceName) {
        "jndi/serviceA" -> {
            log.info("Looking up service A and creating new service for A")
            ServiceImpl("jndi/serviceA")
        }
        "jndi/serviceB" -> {
            log.info("Looking up service B and creating new service for B")
            ServiceImpl("jndi/serviceB")
        }
        else -> null
    }
}