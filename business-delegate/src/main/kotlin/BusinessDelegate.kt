class BusinessDelegate(private val lookupService: BusinessLookup) {
    fun playbackMovie(movie: String) = lookupService.getBusinessService(movie).doProcessing()
}