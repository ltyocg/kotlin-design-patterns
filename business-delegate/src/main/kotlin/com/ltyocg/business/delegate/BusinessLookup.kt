package com.ltyocg.business.delegate

class BusinessLookup(
    private val netflixService: NetflixService,
    private val youTubeService: YouTubeService
) {
    fun getBusinessService(movie: String): VideoStreamingService =
        if (movie.lowercase().contains("die hard")) netflixService else youTubeService
}