package game

import io.github.oshai.kotlinlogging.KotlinLogging

class GameEntity(numEntities: Int) {
    private val logger = KotlinLogging.logger {}
    private val aiComponentManager = AiComponentManager(numEntities)
    private val physicsComponentManager = PhysicsComponentManager(numEntities)
    private val renderComponentManager = RenderComponentManager(numEntities)

    init {
        logger.info { "Init Game with #Entity : $numEntities" }
    }

    fun start() {
        logger.info { "Start Game" }
        aiComponentManager.start()
        physicsComponentManager.start()
        renderComponentManager.start()
    }

    fun update() {
        logger.info { "Update Game Component" }
        aiComponentManager.update()
        physicsComponentManager.update()
        renderComponentManager.update()
    }
}
