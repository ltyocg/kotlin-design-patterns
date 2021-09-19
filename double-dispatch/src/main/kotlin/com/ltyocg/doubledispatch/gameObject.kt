package com.ltyocg.doubledispatch

open class Rectangle(val left: Int, val top: Int, val right: Int, val bottom: Int) {
    fun intersectsWith(r: Rectangle): Boolean = r.left <= right && r.right >= left && r.top <= bottom && r.bottom >= top
    override fun toString(): String = "[$left,$top,$right,$bottom]"
}

abstract class GameObject(left: Int, top: Int, right: Int, bottom: Int) : Rectangle(left, top, right, bottom) {
    var damaged = false
    var onFire = false
    abstract fun collision(gameObject: GameObject)
    abstract fun collisionResolve(asteroid: FlamingAsteroid)
    abstract fun collisionResolve(meteoroid: Meteoroid)
    abstract fun collisionResolve(mir: SpaceStationMir)
    abstract fun collisionResolve(iss: SpaceStationIss)
    override fun toString(): String = "${this::class.simpleName} at ${super.toString()} damaged=$damaged onFire=$onFire"
}