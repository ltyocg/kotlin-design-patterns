import io.github.oshai.kotlinlogging.KotlinLogging

class Scene {
    private val logger = KotlinLogging.logger {}
    private val frameBuffers = Array<Buffer>(2) { FrameBuffer() }
    private var current = 0
    private var next = 1
    val buffer: Buffer
        get() = frameBuffers[current]

    fun draw(coordinateList: List<Pair<Int, Int>>) {
        logger.info { "Start drawing next frame" }
        logger.info { "Current buffer: $current Next buffer: $next" }
        frameBuffers[next].clearAll()
        coordinateList.forEach { (x, y) -> frameBuffers[next].draw(x, y) }
        logger.info { "Swap current and next buffer" }
        swap()
        logger.info { "Finish swapping" }
        logger.info { "Current buffer: $current Next buffer: $next" }
    }

    private fun swap() {
        current = next.also { next = current }
    }
}
