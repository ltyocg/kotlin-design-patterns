import io.github.oshai.kotlinlogging.KotlinLogging

sealed interface Weapon {
    fun wield()
    fun swing()
    fun unwield()
    val enchantment: Enchantment
}

class Hammer(override val enchantment: Enchantment) : Weapon {
    private val logger = KotlinLogging.logger {}
    override fun wield() {
        logger.info { "The hammer is wielded." }
        enchantment.onActivate()
    }

    override fun swing() {
        logger.info { "The hammer is swung." }
        enchantment.apply()
    }

    override fun unwield() {
        logger.info { "The hammer is unwielded." }
        enchantment.onDeactivate()
    }
}

class Sword(override val enchantment: Enchantment) : Weapon {
    private val logger = KotlinLogging.logger {}
    override fun wield() {
        logger.info { "The sword is wielded." }
        enchantment.onActivate()
    }

    override fun swing() {
        logger.info { "The sword is swung." }
        enchantment.apply()
    }

    override fun unwield() {
        logger.info { "The sword is unwielded." }
        enchantment.onDeactivate()
    }
}
