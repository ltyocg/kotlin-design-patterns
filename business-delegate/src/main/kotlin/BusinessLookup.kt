class BusinessLookup(
    private val netflixService: NetflixService,
    private val youTubeService: YouTubeService
) {
    fun getBusinessService(movie: String): VideoStreamingService =
        if ("die hard" in movie.lowercase()) netflixService else youTubeService
}