import io.github.oshai.kotlinlogging.KotlinLogging

interface Troll {
    fun attack()
    val attackPower: Int
    fun fleeBattle()
}

class ClubbedTroll(private val decorated: Troll) : Troll {
    private val logger = KotlinLogging.logger {}
    override fun attack() {
        decorated.attack()
        logger.info { "The troll swings at you with a club!" }
    }

    override val attackPower: Int
        get() = decorated.attackPower + 10

    override fun fleeBattle() = decorated.fleeBattle()
}

class SimpleTroll : Troll {
    private val logger = KotlinLogging.logger {}
    override fun attack() = logger.info { "The troll tries to grab you!" }
    override val attackPower: Int
        get() = 10

    override fun fleeBattle() = logger.info { "The troll shrieks in horror and runs away!" }
}
