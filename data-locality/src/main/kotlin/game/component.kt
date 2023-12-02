package game

import io.github.oshai.kotlinlogging.KotlinLogging

sealed interface Component {
    fun update()
    fun render()
}

data object AiComponent : Component {
    private val logger = KotlinLogging.logger {}
    override fun update() = logger.info { "update AI component" }
    override fun render() {}
}

data object PhysicsComponent : Component {
    private val logger = KotlinLogging.logger {}
    override fun update() = logger.info { "Update physics component of game" }
    override fun render() {}
}

data object RenderComponent : Component {
    private val logger = KotlinLogging.logger {}
    override fun update() {}
    override fun render() = logger.info { "Render Component" }
}
