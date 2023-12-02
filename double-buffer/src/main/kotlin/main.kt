import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() = with(Scene()) {
    draw(listOf(1 to 1, 5 to 6, 3 to 2))
    printBlackPixelCoordinate()
    draw(listOf(3 to 7, 6 to 1))
    printBlackPixelCoordinate()
}

private fun Scene.printBlackPixelCoordinate() {
    logger.info {
        buffer.pixels
            .mapIndexedNotNull { index, pixel ->
                if (pixel == Pixel.BLACK) " (${index % FrameBuffer.WIDTH}, ${index / FrameBuffer.WIDTH}) "
                else null
            }
            .joinToString("", "Black Pixels: ")
    }
}
