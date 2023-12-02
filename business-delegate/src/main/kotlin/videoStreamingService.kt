import io.github.oshai.kotlinlogging.KotlinLogging

sealed interface VideoStreamingService {
    fun doProcessing()
}

data object NetflixService : VideoStreamingService {
    private val logger = KotlinLogging.logger {}
    override fun doProcessing() = logger.info { "NetflixService is now processing" }
}

data object YouTubeService : VideoStreamingService {
    private val logger = KotlinLogging.logger {}
    override fun doProcessing() = logger.info { "YouTubeService is now processing" }
}
