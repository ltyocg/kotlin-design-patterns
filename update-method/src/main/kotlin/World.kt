import org.slf4j.LoggerFactory
import java.security.SecureRandom
import kotlin.concurrent.thread

class World {
    private val log = LoggerFactory.getLogger(javaClass)
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

    private fun processInput() {
        try {
            Thread.sleep((SecureRandom().nextInt(200) + 50).toLong())
        } catch (e: InterruptedException) {
            log.error(e.message)
            Thread.currentThread().interrupt()
        }
    }

    private fun update() = entities.forEach(Entity::update)
    private fun render() {}

    fun run() {
        log.info("Start game.")
        isRunning = true
        thread { gameLoop() }
    }

    fun stop() {
        log.info("Stop game.")
        isRunning = false
    }

    fun addEntity(entity: Entity) {
        entities.add(entity)
    }
}