import org.slf4j.LoggerFactory
import java.awt.event.KeyEvent

private val log = LoggerFactory.getLogger("main")
fun main() {
    log.info("Player Update:")
    GameObject.createPlayer().update(KeyEvent.KEY_LOCATION_LEFT)
    log.info("NPC Update:")
    GameObject.createNpc().demoUpdate()
}