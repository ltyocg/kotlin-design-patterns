import io.github.oshai.kotlinlogging.KotlinLogging

private val log = KotlinLogging.logger {}
fun main() {
    log.info { "The knight receives an enchanted sword." }
    with(Sword(SoulEatingEnchantment)) {
        wield()
        swing()
        unwield()
    }
    log.info { "The valkyrie receives an enchanted hammer." }
    with(Hammer(FlyingEnchantment)) {
        wield()
        swing()
        unwield()
    }
}
