import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")

fun main() {
    log.info(
        Hero(
            Profession.MAGE,
            "Riobard",
            hairColor = HairColor.BLACK,
            weapon = Weapon.DAGGER
        ).toString()
    )
    log.info(
        Hero(
            Profession.WARRIOR,
            "Amberjill",
            HairType.LONG_CURLY,
            HairColor.BLOND,
            Armor.CHAIN_MAIL,
            Weapon.SWORD
        ).toString()
    )
    log.info(
        Hero(
            Profession.THIEF,
            "Desmond",
            HairType.BALD,
            weapon = Weapon.BOW
        ).toString()
    )
}