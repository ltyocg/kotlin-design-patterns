package com.ltyocg.business.delegate

class MobileClient(private val businessDelegate: BusinessDelegate) {
    fun playbackMovie(movie: String) {
        businessDelegate.playbackMovie(movie)
    }
}