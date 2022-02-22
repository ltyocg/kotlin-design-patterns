class MobileClient(private val businessDelegate: BusinessDelegate) {
    fun playbackMovie(movie: String) = businessDelegate.playbackMovie(movie)
}