package com.ltyocg.doubledispatch

import org.slf4j.LoggerFactory

open class SpaceStationMir(left: Int, top: Int, right: Int, bottom: Int) : GameObject(left, top, right, bottom) {
    private val log = LoggerFactory.getLogger(this::class.java)
    override fun collision(gameObject: GameObject) {
        gameObject.collisionResolve(this)
    }

    override fun collisionResolve(asteroid: FlamingAsteroid) {
        val simpleName = this::class.simpleName
        log.info("${Constants.HITS} {} is damaged! {} is set on fire!", asteroid::class.simpleName, simpleName, simpleName, simpleName)
        damaged = true
        onFire = true
    }

    override fun collisionResolve(meteoroid: Meteoroid) {
        val simpleName = this::class.simpleName
        log.info("${Constants.HITS} {} is damaged!", meteoroid::class.simpleName, simpleName, simpleName)
        damaged = true
    }

    override fun collisionResolve(mir: SpaceStationMir) {
        val simpleName = this::class.simpleName
        log.info("${Constants.HITS} {} is damaged!", mir::class.simpleName, simpleName, simpleName)
        damaged = true
    }

    override fun collisionResolve(iss: SpaceStationIss) {
        val simpleName = this::class.simpleName
        log.info("${Constants.HITS} {} is damaged!", iss::class.simpleName, simpleName, simpleName)
        damaged = true
    }
}

class SpaceStationIss(left: Int, top: Int, right: Int, bottom: Int) : SpaceStationMir(left, top, right, bottom) {
    override fun collision(gameObject: GameObject) {
        gameObject.collisionResolve(this)
    }
}