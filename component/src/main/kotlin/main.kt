import io.github.oshai.kotlinlogging.KotlinLogging
import java.awt.event.KeyEvent

private val logger = KotlinLogging.logger {}
fun main() {
    logger.info { "Player Update:" }
    GameObject.createPlayer().update(KeyEvent.KEY_LOCATION_LEFT)
    logger.info { "NPC Update:" }
    GameObject.createNpc().demoUpdate()
}
