import io.github.oshai.kotlinlogging.KotlinLogging

object InitContext {
    private val logger = KotlinLogging.logger {}
    fun lookup(serviceName: String): Any? = when (serviceName) {
        "jndi/serviceA" -> {
            logger.info { "Looking up service A and creating new service for A" }
            ServiceImpl("jndi/serviceA")
        }

        "jndi/serviceB" -> {
            logger.info { "Looking up service B and creating new service for B" }
            ServiceImpl("jndi/serviceB")
        }

        else -> null
    }
}
