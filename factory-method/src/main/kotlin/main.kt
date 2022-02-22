import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")

fun main() {
    with(OrcBlacksmith()) {
        log.info("{} manufactured {}", this, manufactureWeapon(WeaponType.SPEAR))
        log.info("{} manufactured {}", this, manufactureWeapon(WeaponType.AXE))
    }
    with(ElfBlacksmith()) {
        log.info("{} manufactured {}", this, manufactureWeapon(WeaponType.SPEAR))
        log.info("{} manufactured {}", this, manufactureWeapon(WeaponType.AXE))
    }
}