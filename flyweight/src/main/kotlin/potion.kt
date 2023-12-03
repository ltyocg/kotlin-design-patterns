import io.github.oshai.kotlinlogging.KotlinLogging

interface Potion {
    fun drink()
}

class HealingPotion : Potion {
    private val logger = KotlinLogging.logger {}
    override fun drink() = logger.info { "You feel healed. (Potion=${System.identityHashCode(this)})" }
}

class HolyWaterPotion : Potion {
    private val logger = KotlinLogging.logger {}
    override fun drink() = logger.info { "You feel blessed. (Potion=${System.identityHashCode(this)})" }
}

class InvisibilityPotion : Potion {
    private val logger = KotlinLogging.logger {}
    override fun drink() = logger.info { "You become invisible. (Potion=${System.identityHashCode(this)})" }
}

class PoisonPotion : Potion {
    private val logger = KotlinLogging.logger {}
    override fun drink() = logger.info { "Urgh! This is poisonous. (Potion=${System.identityHashCode(this)})" }
}

class StrengthPotion : Potion {
    private val logger = KotlinLogging.logger {}
    override fun drink() = logger.info { "You feel strong. (Potion=${System.identityHashCode(this)})" }
}
