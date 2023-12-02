import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    val factory = WeaponFactory.factory {
        it.add(WeaponType.AXE, ::Axe)
        it.add(WeaponType.BOW, ::Bow)
        it.add(WeaponType.SPEAR, ::Spear)
        it.add(WeaponType.SWORD, ::Sword)
    }
    sequenceOf(WeaponType.AXE, WeaponType.SPEAR, WeaponType.SWORD, WeaponType.BOW)
        .map(factory::create)
        .forEach { logger.info { it } }
}
