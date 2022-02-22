import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")

fun main() {
    log.info("A simple looking troll approaches.")
    val troll = SimpleTroll().also {
        it.attack()
        it.fleeBattle()
        log.info("Simple troll power: {}.\n", it.attackPower)
    }
    log.info("A troll with huge club surprises you.")
    ClubbedTroll(troll).also {
        it.attack()
        it.fleeBattle()
        log.info("Clubbed troll power: {}.\n", it.attackPower)
    }
}