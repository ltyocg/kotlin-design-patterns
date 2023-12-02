import io.github.oshai.kotlinlogging.KotlinLogging

open class Meteoroid(left: Int, top: Int, right: Int, bottom: Int) : GameObject(left, top, right, bottom) {
    private val logger = KotlinLogging.logger {}
    override fun collision(gameObject: GameObject) = gameObject.collisionResolve(this)
    override fun collisionResolve(asteroid: FlamingAsteroid) = logger.info { Constants.hits(asteroid::class.simpleName, this::class.simpleName) }
    override fun collisionResolve(meteoroid: Meteoroid) = logger.info { Constants.hits(meteoroid::class.simpleName, this::class.simpleName) }
    override fun collisionResolve(mir: SpaceStationMir) = logger.info { Constants.hits(mir::class.simpleName, this::class.simpleName) }
    override fun collisionResolve(iss: SpaceStationIss) = logger.info { Constants.hits(iss::class.simpleName, this::class.simpleName) }
}

class FlamingAsteroid(left: Int, top: Int, right: Int, bottom: Int) : Meteoroid(left, top, right, bottom) {
    init {
        onFire = true
    }

    override fun collision(gameObject: GameObject) = gameObject.collisionResolve(this)
}
