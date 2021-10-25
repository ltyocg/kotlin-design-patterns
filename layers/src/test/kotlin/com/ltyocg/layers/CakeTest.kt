package com.ltyocg.layers

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class CakeTest {
    @Test
    fun `test set id`() {
        with(Cake()) {
            assertNull(id)
            val exceptedId = 1234L
            id = exceptedId
            assertEquals(exceptedId, id)
        }
    }

    @Test
    fun `test set topping`() {
        with(Cake()) {
            assertNull(topping)
            val expectedTopping = CakeTopping("DummyTopping", 1000)
            topping = expectedTopping
            assertEquals(expectedTopping, topping)
        }
    }

    @Test
    fun `test set layers`() {
        with(Cake()) {
            assertTrue(layers.isEmpty())
            val expectedLayers = mutableSetOf(
                CakeLayer("layer1", 1000),
                CakeLayer("layer2", 2000),
                CakeLayer("layer3", 3000)
            )
            layers = expectedLayers
            assertEquals(expectedLayers, layers)
        }
    }

    @Test
    fun `test add layer`() {
        with(Cake()) {
            assertTrue(layers.isEmpty())
            val initialLayers = mutableSetOf(CakeLayer("layer1", 1000), CakeLayer("layer2", 2000))
            layers = initialLayers
            assertEquals(initialLayers, layers)
            val newLayer = CakeLayer("layer3", 3000)
            addLayer(newLayer)
            assertEquals(mutableSetOf<CakeLayer>().apply {
                addAll(initialLayers)
                addAll(initialLayers)
                add(newLayer)
            }, layers)
        }
    }

    @Test
    fun `test toString`() {
        assertEquals("id=1234 topping=id=2345 name=topping calories=20 layers=[id=3456 name=layer calories=100]", Cake().apply {
            id = 1234L
            topping = CakeTopping("topping", 20).apply { id = 2345L }
            addLayer(CakeLayer("layer", 100).apply { id = 3456L })
        }.toString())
    }
}