import io.github.oshai.kotlinlogging.KotlinLogging

class ServiceCache {
    private val logger = KotlinLogging.logger {}
    private val serviceCache = mutableMapOf<String, Service>()
    fun getService(serviceName: String): Service? {
        if (serviceCache.containsKey(serviceName)) {
            val cachedService = serviceCache[serviceName]!!
            logger.info { "(cache call) Fetched service ${cachedService.name}(${cachedService.id}) from cache... !" }
            return cachedService
        }
        return null
    }

    fun addService(newService: Service) {
        serviceCache[newService.name] = newService
    }
}
