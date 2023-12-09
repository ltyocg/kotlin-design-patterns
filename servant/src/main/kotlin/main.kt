import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    scenario(Servant("Jenkins"), 1)
    scenario(Servant("Travis"), 0)
}

fun scenario(servant: Servant, compliment: Int) {
    val guests = listOf(Royalty.King(), Royalty.Queen())
    guests.forEach(servant::feed)
    guests.forEach(servant::giveWine)
    servant.giveCompliments(guests[compliment])
    guests.forEach(Royalty::changeMood)
    logger.info {
        if (servant.checkIfYouWillBeHanged(guests)) "${servant.name} will live another day"
        else "Poor ${servant.name}. His days are numbered"
    }
}
