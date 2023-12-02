import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    logger.info { "A simple looking troll approaches." }
    val troll = SimpleTroll().also {
        it.attack()
        it.fleeBattle()
        logger.info { "Simple troll power: ${it.attackPower}.\n" }
    }
    logger.info { "A troll with huge club surprises you." }
    ClubbedTroll(troll).also {
        it.attack()
        it.fleeBattle()
        logger.info { "Clubbed troll power: ${it.attackPower}.\n" }
    }
}
