import org.slf4j.LoggerFactory
import java.io.Closeable

class TreasureChest : Closeable {
    private val log = LoggerFactory.getLogger(javaClass)

    init {
        log.info("Treasure chest opens.")
    }

    override fun close() = log.info("Treasure chest closes.")
}