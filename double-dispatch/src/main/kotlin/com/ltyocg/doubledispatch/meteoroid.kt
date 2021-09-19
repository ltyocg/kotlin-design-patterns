package com.ltyocg.doubledispatch

import org.slf4j.LoggerFactory

open class Meteoroid(left: Int, top: Int, right: Int, bottom: Int) : GameObject(left, top, right, bottom) {
    private val log = LoggerFactory.getLogger(this::class.java)
    override fun collision(gameObject: GameObject) {
        gameObject.collisionResolve(this)
    }

    override fun collisionResolve(asteroid: FlamingAsteroid) {
        log.info(Constants.HITS, asteroid::class.simpleName, this::class.simpleName)
    }

    override fun collisionResolve(meteoroid: Meteoroid) {
        log.info(Constants.HITS, meteoroid::class.simpleName, this::class.simpleName)
    }

    override fun collisionResolve(mir: SpaceStationMir) {
        log.info(Constants.HITS, mir::class.simpleName, this::class.simpleName)
    }

    override fun collisionResolve(iss: SpaceStationIss) {
        log.info(Constants.HITS, iss::class.simpleName, this::class.simpleName)
    }
}

class FlamingAsteroid(left: Int, top: Int, right: Int, bottom: Int) : Meteoroid(left, top, right, bottom) {
    init {
        onFire = true
    }

    override fun collision(gameObject: GameObject) {
        gameObject.collisionResolve(this)
    }
}