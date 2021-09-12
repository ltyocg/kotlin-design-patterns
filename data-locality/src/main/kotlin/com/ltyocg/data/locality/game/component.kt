package com.ltyocg.data.locality.game

import org.slf4j.LoggerFactory

interface Component {
    fun update()
    fun render()
}

class AiComponent : Component {
    private val log = LoggerFactory.getLogger(this::class.java)
    override fun update() {
        log.info("update AI component")
    }

    override fun render() {}
}

class PhysicsComponent : Component {
    private val log = LoggerFactory.getLogger(this::class.java)
    override fun update() {
        log.info("Update physics component of game")
    }

    override fun render() {}
}

class RenderComponent : Component {
    private val log = LoggerFactory.getLogger(this::class.java)
    override fun update() {}
    override fun render() {
        log.info("Render Component")
    }
}