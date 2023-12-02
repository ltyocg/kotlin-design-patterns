interface Buffer {
    fun clear(x: Int, y: Int)
    fun draw(x: Int, y: Int)
    fun clearAll()
    val pixels: Array<Pixel>
}

class FrameBuffer : Buffer {
    override val pixels = Array(WIDTH * HEIGHT) { Pixel.WHITE }
    override fun clear(x: Int, y: Int) {
        pixels[x, y] = Pixel.WHITE
    }

    override fun draw(x: Int, y: Int) {
        pixels[x, y] = Pixel.BLACK
    }

    override fun clearAll() = pixels.fill(Pixel.WHITE)

    private operator fun Array<Pixel>.set(x: Int, y: Int, pixel: Pixel) {
        pixels[x + WIDTH * y] = pixel
    }

    companion object {
        const val WIDTH = 10
        const val HEIGHT = 8
    }
}
