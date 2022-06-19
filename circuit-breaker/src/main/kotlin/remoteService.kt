fun interface RemoteService {
    fun call(): String
}

class DelayedRemoteService(
    private val serverStartTime: Long = System.nanoTime(),
    private val delay: Int = 20
) : RemoteService {
    override fun call(): String {
        if ((System.nanoTime() - serverStartTime) * 1.0 / (1000 * 1000 * 1000) < delay) throw RemoteServiceException("Delayed service is down")
        return "Delayed service is working"
    }
}

object QuickRemoteService : RemoteService {
    override fun call(): String = "Quick Service is working"
}

class RemoteServiceException(message: String?) : Exception(message)