package com.ltyocg.layers

import org.junit.jupiter.api.Assertions
import kotlin.test.*

class CakeBakingServiceTest {
    @Test
    fun `test layers`() {
        val service = CakeBakingService()
        assertTrue(service.getAvailableLayers().isEmpty())
        service.saveNewLayer(CakeLayerInfo(name = "Layer1", calories = 1000))
        service.saveNewLayer(CakeLayerInfo(name = "Layer2", calories = 2000))
        val availableLayers = service.getAvailableLayers()
        assertEquals(2, availableLayers.size)
        availableLayers.forEach {
            assertNotNull(it.id)
            assertNotNull(it.name)
            assertTrue(it.calories > 0)
        }
    }

    @Test
    fun `test toppings`() {
        val service = CakeBakingService()
        assertTrue(service.getAvailableToppings().isEmpty())
        service.saveNewTopping(CakeToppingInfo(name = "Topping1", calories = 1000))
        service.saveNewTopping(CakeToppingInfo(name = "Topping2", calories = 2000))
        val availableToppings = service.getAvailableToppings()
        assertEquals(2, availableToppings.size)
        availableToppings.forEach {
            assertNotNull(it.id)
            assertNotNull(it.name)
            assertTrue(it.calories > 0)
        }
    }

    @Test
    fun `test bake cakes`() {
        val service = CakeBakingService()
        assertTrue(service.getAllCakes().isEmpty())
        val topping1 = CakeToppingInfo(name = "Topping1", calories = 1000)
        val topping2 = CakeToppingInfo(name = "Topping2", calories = 2000)
        service.saveNewTopping(topping1)
        service.saveNewTopping(topping2)
        val layer1 = CakeLayerInfo(name = "Layer1", calories = 1000)
        val layer2 = CakeLayerInfo(name = "Layer2", calories = 2000)
        val layer3 = CakeLayerInfo(name = "Layer3", calories = 2000)
        service.saveNewLayer(layer1)
        service.saveNewLayer(layer2)
        service.saveNewLayer(layer3)
        service.bakeNewCake(CakeInfo(cakeToppingInfo = topping1, cakeLayerInfos = listOf(layer1, layer2)))
        service.bakeNewCake(CakeInfo(cakeToppingInfo = topping2, cakeLayerInfos = listOf(layer3)))
        val allCakes = service.getAllCakes()
        assertEquals(2, allCakes.size)
        for (cakeInfo in allCakes) {
            assertNotNull(cakeInfo.id)
            assertNotNull(cakeInfo.cakeToppingInfo)
            assertNotNull(cakeInfo.cakeLayerInfos)
            assertNotNull(cakeInfo.toString())
            assertFalse(cakeInfo.cakeLayerInfos.isEmpty())
            Assertions.assertTrue(cakeInfo.calculateTotalCalories > 0)
        }
    }

    @Test
    fun `test bake cake missing topping`() {
        val service = CakeBakingService()
        val layer1 = CakeLayerInfo(name = "Layer1", calories = 1000)
        val layer2 = CakeLayerInfo(name = "Layer2", calories = 2000)
        service.saveNewLayer(layer1)
        service.saveNewLayer(layer2)
        assertFailsWith<CakeBakingException> {
            service.bakeNewCake(CakeInfo(cakeToppingInfo = CakeToppingInfo(name = "Topping1", calories = 1000), cakeLayerInfos = listOf(layer1, layer2)))
        }
    }

    @Test
    fun `test bake cake missing layer`() {
        val service = CakeBakingService()
        assertTrue(service.getAllCakes().isEmpty())
        val topping1 = CakeToppingInfo(name = "Topping1", calories = 1000)
        service.saveNewTopping(topping1)
        val layer1 = CakeLayerInfo(name = "Layer1", calories = 1000)
        service.saveNewLayer(layer1)
        assertFailsWith<CakeBakingException> {
            service.bakeNewCake(CakeInfo(cakeToppingInfo = topping1, cakeLayerInfos = listOf(layer1, CakeLayerInfo(name = "Layer2", calories = 2000))))
        }
    }

    @Test
    fun `test bake cakes used layer`() {
        val service = CakeBakingService()
        assertTrue(service.getAllCakes().isEmpty())
        val topping1 = CakeToppingInfo(name = "Topping1", calories = 1000)
        val topping2 = CakeToppingInfo(name = "Topping2", calories = 2000)
        service.saveNewTopping(topping1)
        service.saveNewTopping(topping2)
        val layer1 = CakeLayerInfo(name = "Layer1", calories = 1000)
        val layer2 = CakeLayerInfo(name = "Layer2", calories = 2000)
        service.saveNewLayer(layer1)
        service.saveNewLayer(layer2)
        service.bakeNewCake(CakeInfo(cakeToppingInfo = topping1, cakeLayerInfos = listOf(layer1, layer2)))
        assertFailsWith<CakeBakingException> {
            service.bakeNewCake(CakeInfo(cakeToppingInfo = topping2, cakeLayerInfos = listOf(layer2)))
        }
    }
}