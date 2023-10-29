import io.github.oshai.kotlinlogging.KotlinLogging

object FishingBoat {
    private val logger = KotlinLogging.logger {}
    fun sail() = logger.info { "The fishing boat is sailing" }
}

interface RowingBoat {
    fun row()
}

object FishingBoatAdapter : RowingBoat {
    override fun row() = FishingBoat.sail()
}
