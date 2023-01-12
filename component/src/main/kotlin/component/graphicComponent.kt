package component

import GameObject
import org.slf4j.LoggerFactory

interface GraphicComponent {
    fun update(gameObject: GameObject)
}

class ObjectGraphicComponent : GraphicComponent {
    private companion object {
        private val log = LoggerFactory.getLogger(ObjectGraphicComponent::class.java)
    }

    override fun update(gameObject: GameObject) = log.info("{}'s current velocity: {}", gameObject.name, gameObject.velocity)
}