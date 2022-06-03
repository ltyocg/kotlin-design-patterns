object ServiceLocator {
    private val serviceCache = ServiceCache()
    fun getService(serviceJndiName: String): Service? {
        val serviceObj = serviceCache.getService(serviceJndiName) ?: InitContext.lookup(serviceJndiName) as Service?
        if (serviceObj != null) serviceCache.addService(serviceObj)
        return serviceObj
    }
}