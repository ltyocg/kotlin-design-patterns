import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    SlidingDoor().use { log.info("Walking in.") }
    TreasureChest().use { log.info("Looting contents.") }
}