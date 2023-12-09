import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    CharacterStepBuilder.newBuilder()
        .name("Amberjill")
        .fighterClass("Paladin")
        .withWeapon("Sword")
        .noAbilities()
        .build()
        .let { logger.info { it } }
    CharacterStepBuilder.newBuilder()
        .name("Riobard")
        .wizardClass("Sorcerer")
        .withSpell("Fireball")
        .withAbility("Fire Aura")
        .withAbility("Teleport")
        .noMoreAbilities()
        .build()
        .let { logger.info { it } }
    CharacterStepBuilder.newBuilder()
        .name("Desmond")
        .fighterClass("Rogue")
        .noWeapon()
        .build()
        .let { logger.info { it } }
}
