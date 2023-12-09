import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.Closeable

class TreasureChest : Closeable {
    private val logger = KotlinLogging.logger {}

    init {
        logger.info { "Treasure chest opens." }
    }

    override fun close() = logger.info { "Treasure chest closes." }
}
