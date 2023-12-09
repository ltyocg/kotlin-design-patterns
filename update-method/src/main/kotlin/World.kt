import io.github.oshai.kotlinlogging.KotlinLogging
import java.security.SecureRandom
import kotlin.concurrent.thread

class World {
    private val logger = KotlinLogging.logger {}
    var entities = mutableListOf<Entity>()

    @Volatile
    var isRunning = false

    private fun gameLoop() {
        while (isRunning) {
            processInput()
            update()
            render()
        }
    }

    private fun processInput() = try {
        Thread.sleep((SecureRandom().nextInt(200) + 50).toLong())
    } catch (e: InterruptedException) {
        logger.error { e.message }
        Thread.currentThread().interrupt()
    }

    private fun update() = entities.forEach(Entity::update)
    private fun render() {}

    fun run() {
        logger.info { "Start game." }
        isRunning = true
        thread { gameLoop() }
    }

    fun stop() {
        logger.info { "Stop game." }
        isRunning = false
    }

    fun addEntity(entity: Entity) {
        entities.add(entity)
    }
}
