import org.slf4j.LoggerFactory

sealed interface VideoStreamingService {
    fun doProcessing()
}

object NetflixService : VideoStreamingService {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun doProcessing() = log.info("NetflixService is now processing")
}

object YouTubeService : VideoStreamingService {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun doProcessing() = log.info("YouTubeService is now processing")
}
