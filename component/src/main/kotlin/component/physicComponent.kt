package component

import GameObject
import io.github.oshai.kotlinlogging.KotlinLogging

interface PhysicComponent {
    fun update(gameObject: GameObject)
}

class ObjectPhysicComponent : PhysicComponent {
    private val logger = KotlinLogging.logger {}
    override fun update(gameObject: GameObject) {
        gameObject.updateCoordinate()
        logger.info { "${gameObject.name}'s coordinate has been changed." }
    }
}
