package component

import GameObject
import io.github.oshai.kotlinlogging.KotlinLogging

interface GraphicComponent {
    fun update(gameObject: GameObject)
}

class ObjectGraphicComponent : GraphicComponent {
    private val logger = KotlinLogging.logger {}
    override fun update(gameObject: GameObject) = logger.info { "${gameObject.name}'s current velocity: ${gameObject.velocity}" }
}
