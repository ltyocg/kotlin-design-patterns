import layers.*
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.*

@SpringBootTest(classes = [App::class])
class CakeBakingServiceTest(private val cakeBakingService: CakeBakingService) {
    @BeforeTest
    fun setUp() {
        cakeBakingService.deleteAllCakes()
        cakeBakingService.deleteAllLayers()
        cakeBakingService.deleteAllToppings()
    }

    @Test
    fun layers() {
        assertTrue(cakeBakingService.getAvailableLayers().isEmpty())
        cakeBakingService.saveNewLayer(CakeLayerInfo(name = "Layer1", calories = 1000))
        cakeBakingService.saveNewLayer(CakeLayerInfo(name = "Layer2", calories = 2000))
        val availableLayers = cakeBakingService.getAvailableLayers()
        assertEquals(2, availableLayers.size)
        availableLayers.forEach {
            assertNotNull(it.id)
            assertNotNull(it.name)
            assertTrue(it.calories > 0)
        }
    }

    @Test
    fun toppings() {
        assertTrue(cakeBakingService.getAvailableToppings().isEmpty())
        cakeBakingService.saveNewTopping(CakeToppingInfo(name = "Topping1", calories = 1000))
        cakeBakingService.saveNewTopping(CakeToppingInfo(name = "Topping2", calories = 2000))
        val availableToppings = cakeBakingService.getAvailableToppings()
        assertEquals(2, availableToppings.size)
        availableToppings.forEach {
            assertNotNull(it.id)
            assertNotNull(it.name)
            assertTrue(it.calories > 0)
        }
    }

    @Test
    fun `bake cakes`() {
        assertTrue(cakeBakingService.getAllCakes().isEmpty())
        val topping1 = CakeToppingInfo(name = "Topping1", calories = 1000)
        val topping2 = CakeToppingInfo(name = "Topping2", calories = 2000)
        cakeBakingService.saveNewTopping(topping1)
        cakeBakingService.saveNewTopping(topping2)
        val layer1 = CakeLayerInfo(name = "Layer1", calories = 1000)
        val layer2 = CakeLayerInfo(name = "Layer2", calories = 2000)
        val layer3 = CakeLayerInfo(name = "Layer3", calories = 2000)
        cakeBakingService.saveNewLayer(layer1)
        cakeBakingService.saveNewLayer(layer2)
        cakeBakingService.saveNewLayer(layer3)
        cakeBakingService.bakeNewCake(CakeInfo(cakeToppingInfo = topping1, cakeLayerInfos = listOf(layer1, layer2)))
        cakeBakingService.bakeNewCake(CakeInfo(cakeToppingInfo = topping2, cakeLayerInfos = listOf(layer3)))
        val allCakes = cakeBakingService.getAllCakes()
        assertEquals(2, allCakes.size)
        for (cakeInfo in allCakes) {
            assertNotNull(cakeInfo.id)
            assertNotNull(cakeInfo.cakeToppingInfo)
            assertNotNull(cakeInfo.cakeLayerInfos)
            assertNotNull(cakeInfo.toString())
            assertFalse(cakeInfo.cakeLayerInfos.isEmpty())
            assertTrue(cakeInfo.calculateTotalCalories() > 0)
        }
    }

    @Test
    fun `bake cake missing topping`() {
        val layer1 = CakeLayerInfo(name = "Layer1", calories = 1000)
        val layer2 = CakeLayerInfo(name = "Layer2", calories = 2000)
        cakeBakingService.saveNewLayer(layer1)
        cakeBakingService.saveNewLayer(layer2)
        assertFailsWith<CakeBakingException> {
            cakeBakingService.bakeNewCake(
                CakeInfo(
                    cakeToppingInfo = CakeToppingInfo(name = "Topping1", calories = 1000),
                    cakeLayerInfos = listOf(layer1, layer2)
                )
            )
        }
    }

    @Test
    fun `bake cake missing layer`() {
        assertTrue(cakeBakingService.getAllCakes().isEmpty())
        val topping1 = CakeToppingInfo(name = "Topping1", calories = 1000)
        cakeBakingService.saveNewTopping(topping1)
        val layer1 = CakeLayerInfo(name = "Layer1", calories = 1000)
        cakeBakingService.saveNewLayer(layer1)
        assertFailsWith<CakeBakingException> {
            cakeBakingService.bakeNewCake(
                CakeInfo(
                    cakeToppingInfo = topping1,
                    cakeLayerInfos = listOf(layer1, CakeLayerInfo(name = "Layer2", calories = 2000))
                )
            )
        }
    }

    @Test
    fun `bake cakes used layer`() {
        assertTrue(cakeBakingService.getAllCakes().isEmpty())
        val topping1 = CakeToppingInfo(name = "Topping1", calories = 1000)
        val topping2 = CakeToppingInfo(name = "Topping2", calories = 2000)
        cakeBakingService.saveNewTopping(topping1)
        cakeBakingService.saveNewTopping(topping2)
        val layer1 = CakeLayerInfo(name = "Layer1", calories = 1000)
        val layer2 = CakeLayerInfo(name = "Layer2", calories = 2000)
        cakeBakingService.saveNewLayer(layer1)
        cakeBakingService.saveNewLayer(layer2)
        cakeBakingService.bakeNewCake(CakeInfo(cakeToppingInfo = topping1, cakeLayerInfos = listOf(layer1, layer2)))
        assertFailsWith<CakeBakingException> {
            cakeBakingService.bakeNewCake(CakeInfo(cakeToppingInfo = topping2, cakeLayerInfos = listOf(layer2)))
        }
    }
}
