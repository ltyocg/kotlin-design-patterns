package com.ltyocg.doublebuffer

import com.ltyocg.commons.FieldAccessor
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class FrameBufferTest {
    @Test
    fun `test clearAll`() {
        assertEquals(Pixel.WHITE, FrameBuffer().apply {
            accessor["pixels"] = samplePixels()
            clearAll()
        }.pixels[0])
    }

    @Test
    fun `test clear`() {
        assertEquals(Pixel.WHITE, FrameBuffer().apply {
            accessor["pixels"] = samplePixels()
            clear(0, 0)
        }.pixels[0])
    }

    @Test
    fun `test draw`() {
        assertEquals(Pixel.BLACK, FrameBuffer().apply { draw(0, 0) }.pixels[0])
    }

    @Test
    fun `test pixels`() {
        val pixels = samplePixels()
        assertContentEquals(pixels, FrameBuffer().apply { accessor["pixels"] = pixels }.pixels)
    }

    private fun samplePixels(): Array<Pixel> = Array(FrameBuffer.WIDTH * FrameBuffer.HEIGHT) { Pixel.WHITE }.apply { this[0] = Pixel.BLACK }
    private val FrameBuffer.accessor: FieldAccessor<FrameBuffer>
        get() = FieldAccessor(this)
}