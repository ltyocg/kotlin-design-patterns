import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    scenario(Servant("Jenkins"), 1)
    scenario(Servant("Travis"), 0)
}

fun scenario(servant: Servant, compliment: Int) {
    val k = King()
    val q = Queen()
    val guests = listOf(k, q)
    servant.feed(k)
    servant.feed(q)
    servant.giveWine(k)
    servant.giveWine(q)
    servant.giveCompliments(guests[compliment])
    guests.forEach { it.changeMood() }
    if (servant.checkIfYouWillBeHanged(guests)) log.info("{} will live another day", servant.name)
    else log.info("Poor {}. His days are numbered", servant.name)
}