package game

import org.slf4j.LoggerFactory

class GameEntity(numEntities: Int) {
    private val log = LoggerFactory.getLogger(javaClass)
    private val aiComponentManager: AiComponentManager = AiComponentManager(numEntities)
    private val physicsComponentManager: PhysicsComponentManager = PhysicsComponentManager(numEntities)
    private val renderComponentManager: RenderComponentManager = RenderComponentManager(numEntities)

    init {
        log.info("Init Game with #Entity : {}", numEntities)
    }

    fun start() {
        log.info("Start Game")
        aiComponentManager.start()
        physicsComponentManager.start()
        renderComponentManager.start()
    }

    fun update() {
        log.info("Update Game Component")
        aiComponentManager.update()
        physicsComponentManager.update()
        renderComponentManager.update()
    }
}