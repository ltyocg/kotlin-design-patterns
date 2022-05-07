import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
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
    if (servant.checkIfYouWillBeHanged(guests)) log.info("{} will live another day", servant.name)
    else log.info("Poor {}. His days are numbered", servant.name)
}