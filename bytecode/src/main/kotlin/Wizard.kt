import io.github.oshai.kotlinlogging.KotlinLogging

class Wizard(
    var health: Int,
    var agility: Int,
    var wisdom: Int,
    numberOfPlayedSounds: Int,
    numberOfSpawnedParticles: Int
) {
    private val logger = KotlinLogging.logger {}
    var numberOfPlayedSounds = numberOfPlayedSounds
        private set
    var numberOfSpawnedParticles = numberOfSpawnedParticles
        private set

    fun playSound() {
        logger.info { "Playing sound" }
        numberOfPlayedSounds++
    }

    fun spawnParticles() {
        logger.info { "Spawning particles" }
        numberOfSpawnedParticles++
    }
}
