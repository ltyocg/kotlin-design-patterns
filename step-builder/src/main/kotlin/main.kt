import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    CharacterStepBuilder.newBuilder()
        .name("Amberjill")
        .fighterClass("Paladin")
        .withWeapon("Sword")
        .noAbilities()
        .build()
        .run { log.info(toString()) }
    CharacterStepBuilder.newBuilder()
        .name("Riobard")
        .wizardClass("Sorcerer")
        .withSpell("Fireball")
        .withAbility("Fire Aura")
        .withAbility("Teleport")
        .noMoreAbilities()
        .build()
        .run { log.info(toString()) }
    CharacterStepBuilder.newBuilder()
        .name("Desmond")
        .fighterClass("Rogue")
        .noWeapon()
        .build()
        .run { log.info(toString()) }
}