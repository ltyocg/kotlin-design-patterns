import io.github.oshai.kotlinlogging.KotlinLogging

sealed interface Enchantment {
    fun onActivate()
    fun apply()
    fun onDeactivate()
}

data object FlyingEnchantment : Enchantment {
    private val logger = KotlinLogging.logger {}
    override fun onActivate() = logger.info { "The item begins to glow faintly." }
    override fun apply() = logger.info { "The item flies and strikes the enemies finally returning to owner's hand." }
    override fun onDeactivate() = logger.info { "The item's glow fades." }
}

data object SoulEatingEnchantment : Enchantment {
    private val logger = KotlinLogging.logger {}
    override fun onActivate() = logger.info { "The item spreads bloodlust." }
    override fun apply() = logger.info { "The item eats the soul of enemies." }
    override fun onDeactivate() = logger.info { "Bloodlust slowly disappears." }
}
