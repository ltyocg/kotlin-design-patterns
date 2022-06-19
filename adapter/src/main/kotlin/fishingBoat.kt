import org.slf4j.LoggerFactory

object FishingBoat {
    private val log = LoggerFactory.getLogger(javaClass)
    fun sail() = log.info("The fishing boat is sailing")
}

interface RowingBoat {
    fun row()
}

object FishingBoatAdapter : RowingBoat {
    override fun row() = FishingBoat.sail()
}