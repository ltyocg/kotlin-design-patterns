package game

import io.github.oshai.kotlinlogging.KotlinLogging

private const val MAX_ENTITIES = 10000

class AiComponentManager(private val numEntities: Int) {
    private val logger = KotlinLogging.logger {}
    private val aiComponents = arrayOfNulls<Component>(MAX_ENTITIES)
    fun start() {
        logger.info { "Start AI Game Component" }
        repeat(numEntities) { aiComponents[it] = AiComponent }
    }

    fun update() {
        logger.info { "Update AI Game Component" }
        aiComponents.asSequence()
            .take(numEntities)
            .filterNotNull()
            .forEach { it.update() }
    }
}

class PhysicsComponentManager(private val numEntities: Int) {
    private val logger = KotlinLogging.logger {}
    private val physicsComponents = arrayOfNulls<PhysicsComponent>(MAX_ENTITIES)
    fun start() {
        logger.info { "Start Physics Game Component " }
        repeat(numEntities) { physicsComponents[it] = PhysicsComponent }
    }

    fun update() {
        logger.info { "Update Physics Game Component " }
        physicsComponents.asSequence()
            .take(numEntities)
            .filterNotNull()
            .forEach { it.update() }
    }
}

class RenderComponentManager(private val numEntities: Int) {
    private val logger = KotlinLogging.logger {}
    private val renderComponents = arrayOfNulls<RenderComponent>(MAX_ENTITIES)
    fun start() {
        logger.info { "Start Render Game Component " }
        repeat(numEntities) { renderComponents[it] = RenderComponent }
    }

    fun update() {
        logger.info { "Update Render Game Component " }
        renderComponents.asSequence()
            .take(numEntities)
            .filterNotNull()
            .forEach { it.render() }
    }
}
