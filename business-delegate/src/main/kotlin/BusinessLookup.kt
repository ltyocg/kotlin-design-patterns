class BusinessLookup {
    fun getBusinessService(movie: String): VideoStreamingService =
        if ("die hard" in movie.lowercase()) NetflixService else YouTubeService
}