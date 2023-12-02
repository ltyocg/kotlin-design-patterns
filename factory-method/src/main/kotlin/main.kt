import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}

fun main() {
    with(OrcBlacksmith()) {
        logger.info { "$this manufactured ${manufactureWeapon(WeaponType.SPEAR)}" }
        logger.info { "$this manufactured ${manufactureWeapon(WeaponType.AXE)}" }
    }
    with(ElfBlacksmith()) {
        logger.info { "$this manufactured ${manufactureWeapon(WeaponType.SPEAR)}" }
        logger.info { "$this manufactured ${manufactureWeapon(WeaponType.AXE)}" }
    }
}
