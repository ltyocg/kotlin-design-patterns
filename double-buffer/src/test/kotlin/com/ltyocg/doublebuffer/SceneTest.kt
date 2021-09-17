package com.ltyocg.doublebuffer

import com.ltyocg.commons.FieldAccessor
import kotlin.test.Test
import kotlin.test.assertEquals

class SceneTest {
    @Test
    fun `test buffer`() {
        val frameBuffer = FrameBuffer().apply { draw(0, 0) }
        assertEquals(frameBuffer, Scene().apply {
            accessor["current"] = 0
            accessor["frameBuffers"] = arrayOf(frameBuffer)
        }.buffer)
    }

    @Test
    fun `test draw`() {
        Scene().apply {
            accessor["current"] = 0
            accessor["next"] = 1
            draw(emptyList())
            assertEquals(1, accessor["current"])
            assertEquals(0, accessor["next"])
        }
    }
}

private val Scene.accessor: FieldAccessor<Scene>
    get() = FieldAccessor(this)