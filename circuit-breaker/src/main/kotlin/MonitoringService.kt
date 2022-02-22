class MonitoringService(private val delayedService: CircuitBreaker?, private val quickService: CircuitBreaker?) {
    fun localResourceResponse(): String = "Local Service is working"
    fun delayedServiceResponse(): String? = try {
        delayedService!!.attemptRequest()
    } catch (e: RemoteServiceException) {
        e.localizedMessage
    }

    fun quickServiceResponse(): String? = try {
        quickService!!.attemptRequest()
    } catch (e: RemoteServiceException) {
        e.localizedMessage
    }
}