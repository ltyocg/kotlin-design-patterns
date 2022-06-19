package game

import org.slf4j.LoggerFactory

sealed interface Component {
    fun update()
    fun render()
}

object AiComponent : Component {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun update() {
        log.info("update AI component")
    }

    override fun render() {}
}

object PhysicsComponent : Component {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun update() {
        log.info("Update physics component of game")
    }

    override fun render() {}
}

object RenderComponent : Component {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun update() {}
    override fun render() {
        log.info("Render Component")
    }
}