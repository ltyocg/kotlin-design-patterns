package component

import GameObject
import org.slf4j.LoggerFactory

interface PhysicComponent {
    fun update(gameObject: GameObject)
}

class ObjectPhysicComponent : PhysicComponent {
    private companion object {
        private val log = LoggerFactory.getLogger(ObjectPhysicComponent::class.java)
    }

    override fun update(gameObject: GameObject) {
        gameObject.updateCoordinate()
        log.info("{}'s coordinate has been changed.", gameObject.name)
    }
}