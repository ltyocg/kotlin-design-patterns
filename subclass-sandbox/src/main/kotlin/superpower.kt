import org.slf4j.LoggerFactory
import kotlin.reflect.KClass

sealed class Superpower(kClass: KClass<out Superpower>) {
    private val logger = LoggerFactory.getLogger(kClass.java)
    protected abstract fun activate()
    fun move(x: Double, y: Double, z: Double) = logger.info("Move to ( {}, {}, {} )", x, y, z)
    fun playSound(soundName: String, volumn: Int) = logger.info("Play {} with volumn {}", soundName, volumn)
    fun spawnParticles(particleType: String, count: Int) = logger.info("Spawn {} particle with type {}", count, particleType)
}

class GroundDive : Superpower(GroundDive::class) {
    public override fun activate() {
        move(0.0, 0.0, -20.0)
        playSound("GROUNDDIVE_SOUND", 5)
        spawnParticles("GROUNDDIVE_PARTICLE", 20)
    }
}

class SkyLaunch : Superpower(SkyLaunch::class) {
    public override fun activate() {
        move(0.0, 0.0, 20.0)
        playSound("SKYLAUNCH_SOUND", 1)
        spawnParticles("SKYLAUNCH_PARTICLE", 100)
    }
}