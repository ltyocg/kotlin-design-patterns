package com.ltyocg.data.locality.game

import org.slf4j.LoggerFactory

private const val MAX_ENTITIES = 10000

class AiComponentManager(private val numEntities: Int) {
    private val log = LoggerFactory.getLogger(javaClass)
    private val aiComponents = arrayOfNulls<Component>(MAX_ENTITIES)
    fun start() {
        log.info("Start AI Game Component")
        repeat(numEntities) { aiComponents[it] = AiComponent() }
    }

    fun update() {
        log.info("Update AI Game Component")
        aiComponents.asSequence()
            .take(numEntities)
            .filterNotNull()
            .forEach { it.update() }
    }
}

class PhysicsComponentManager(private val numEntities: Int) {
    private val log = LoggerFactory.getLogger(javaClass)
    private val physicsComponents = arrayOfNulls<PhysicsComponent>(MAX_ENTITIES)
    fun start() {
        log.info("Start Physics Game Component ")
        repeat(numEntities) { physicsComponents[it] = PhysicsComponent() }
    }

    fun update() {
        log.info("Update Physics Game Component ")
        physicsComponents.asSequence()
            .take(numEntities)
            .filterNotNull()
            .forEach { it.update() }
    }
}

class RenderComponentManager(private val numEntities: Int) {
    private val log = LoggerFactory.getLogger(javaClass)
    private val renderComponents = arrayOfNulls<RenderComponent>(MAX_ENTITIES)
    fun start() {
        log.info("Start Render Game Component ")
        repeat(numEntities) { renderComponents[it] = RenderComponent() }
    }

    fun update() {
        log.info("Update Render Game Component ")
        renderComponents.asSequence()
            .take(numEntities)
            .filterNotNull()
            .forEach { it.render() }
    }
}