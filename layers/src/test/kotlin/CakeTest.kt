import layers.Cake
import layers.CakeLayer
import layers.CakeTopping
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class CakeTest {
    @Test
    fun `set id`() {
        with(Cake()) {
            assertNull(id)
            val exceptedId = 1234L
            id = exceptedId
            assertEquals(exceptedId, id)
        }
    }

    @Test
    fun `set topping`() {
        with(Cake()) {
            assertNull(topping)
            val expectedTopping = CakeTopping(name = "DummyTopping", calories = 1000)
            topping = expectedTopping
            assertEquals(expectedTopping, topping)
        }
    }

    @Test
    fun `set layers`() {
        with(Cake()) {
            assertTrue(layers.isEmpty())
            val expectedLayers = mutableSetOf(
                CakeLayer(name = "layer1", calories = 1000),
                CakeLayer(name = "layer2", calories = 2000),
                CakeLayer(name = "layer3", calories = 3000)
            )
            layers = expectedLayers
            assertEquals(expectedLayers, layers)
        }
    }

    @Test
    fun addLayer() {
        with(Cake()) {
            assertTrue(layers.isEmpty())
            val initialLayers = mutableSetOf(CakeLayer(name = "layer1", calories = 1000), CakeLayer(name = "layer2", calories = 2000))
            layers = initialLayers
            assertEquals(initialLayers, layers)
            val newLayer = CakeLayer(name = "layer3", calories = 3000)
            addLayer(newLayer)
            assertEquals(buildSet {
                addAll(initialLayers)
                addAll(initialLayers)
                add(newLayer)
            }, layers)
        }
    }

    @Test
    fun `test toString`() {
        assertEquals(
            "id=1234 topping=id=2345 name=topping calories=20 layers=[id=3456 name=layer calories=100]",
            Cake(
                1234L,
                CakeTopping(2345L, "topping", 20)
            ).apply {
                addLayer(CakeLayer(3456L, "layer", 100))
            }.toString()
        )
    }
}