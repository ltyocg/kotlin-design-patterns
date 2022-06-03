import org.slf4j.LoggerFactory

class ServiceCache {
    private val log = LoggerFactory.getLogger(javaClass)
    private val serviceCache = mutableMapOf<String, Service>()
    fun getService(serviceName: String): Service? {
        if (serviceCache.containsKey(serviceName)) {
            val cachedService = serviceCache[serviceName]!!
            log.info("(cache call) Fetched service {}({}) from cache... !", cachedService.name, cachedService.id)
            return cachedService
        }
        return null
    }

    fun addService(newService: Service) {
        serviceCache[newService.name] = newService
    }
}