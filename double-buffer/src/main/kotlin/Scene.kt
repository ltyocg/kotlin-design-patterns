import org.slf4j.LoggerFactory

class Scene {
    private val log = LoggerFactory.getLogger(javaClass)
    private val frameBuffers = Array<Buffer>(2) { FrameBuffer() }
    private var current = 0
    private var next = 1
    val buffer: Buffer
        get() = frameBuffers[current]

    fun draw(coordinateList: List<Pair<Int, Int>>) {
        log.info("Start drawing next frame")
        log.info("Current buffer: {} Next buffer: {}", current, next)
        frameBuffers[next].clearAll()
        coordinateList.forEach { (x, y) -> frameBuffers[next].draw(x, y) }
        log.info("Swap current and next buffer")
        swap()
        log.info("Finish swapping")
        log.info("Current buffer: {} Next buffer: {}", current, next)
    }

    private fun swap() {
        current = next.also { next = current }
    }
}