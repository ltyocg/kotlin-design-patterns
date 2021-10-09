package com.ltyocg.gameloop

class GameController {
    internal val bullet = Bullet()
    fun moveBullet(offset: Float) {
        bullet.position += offset
    }

    val bulletPosition: Float
        get() = bullet.position
}