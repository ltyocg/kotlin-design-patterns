import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    logger.info { "The knight receives an enchanted sword." }
    with(Sword(SoulEatingEnchantment)) {
        wield()
        swing()
        unwield()
    }
    logger.info { "The valkyrie receives an enchanted hammer." }
    with(Hammer(FlyingEnchantment)) {
        wield()
        swing()
        unwield()
    }
}
