import org.slf4j.LoggerFactory

class Wizard(
    var health: Int,
    var agility: Int,
    var wisdom: Int,
    numberOfPlayedSounds: Int,
    numberOfSpawnedParticles: Int
) {
    private val log = LoggerFactory.getLogger(javaClass)
    var numberOfPlayedSounds = numberOfPlayedSounds
        private set
    var numberOfSpawnedParticles = numberOfSpawnedParticles
        private set

    fun playSound() {
        log.info("Playing sound")
        numberOfPlayedSounds++
    }

    fun spawnParticles() {
        log.info("Spawning particles")
        numberOfSpawnedParticles++
    }
}