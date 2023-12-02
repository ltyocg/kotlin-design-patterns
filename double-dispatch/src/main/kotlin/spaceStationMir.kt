import io.github.oshai.kotlinlogging.KotlinLogging

open class SpaceStationMir(left: Int, top: Int, right: Int, bottom: Int) : GameObject(left, top, right, bottom) {
    private val logger = KotlinLogging.logger {}
    override fun collision(gameObject: GameObject) = gameObject.collisionResolve(this)
    override fun collisionResolve(asteroid: FlamingAsteroid) {
        logger.info {
            val simpleName = this::class.simpleName
            "${Constants.hits(asteroid::class.simpleName, simpleName)} $simpleName is damaged! $simpleName is set on fire!"
        }
        damaged = true
        onFire = true
    }

    override fun collisionResolve(meteoroid: Meteoroid) {
        logger.info {
            val simpleName = this::class.simpleName
            "${Constants.hits(meteoroid::class.simpleName, simpleName)} $simpleName is damaged!"
        }
        damaged = true
    }

    override fun collisionResolve(mir: SpaceStationMir) {
        logger.info {
            val simpleName = this::class.simpleName
            "${Constants.hits(mir::class.simpleName, simpleName)} $simpleName is damaged!"
        }
        damaged = true
    }

    override fun collisionResolve(iss: SpaceStationIss) {
        logger.info {
            val simpleName = this::class.simpleName
            "${Constants.hits(iss::class.simpleName, simpleName)} $simpleName is damaged!"
        }
        damaged = true
    }
}

class SpaceStationIss(left: Int, top: Int, right: Int, bottom: Int) : SpaceStationMir(left, top, right, bottom) {
    override fun collision(gameObject: GameObject) = gameObject.collisionResolve(this)
}
