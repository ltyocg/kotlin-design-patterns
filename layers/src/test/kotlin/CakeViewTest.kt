import com.ltyocg.commons.assertLogContains
import layers.*
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test

class CakeViewTest {
    @Test
    fun render() {
        val cake = CakeInfo(
            cakeToppingInfo = CakeToppingInfo(name = "topping", calories = 1000),
            cakeLayerInfos = listOf(
                CakeLayerInfo(name = "layer1", calories = 1000),
                CakeLayerInfo(name = "layer2", calories = 2000),
                CakeLayerInfo(name = "layer3", calories = 3000)
            )
        )
        val bakingService = mock<CakeBakingService>()
        whenever(bakingService.getAllCakes()).thenReturn(listOf(cake))
        assertLogContains(cake.toString()) { CakeView(bakingService).render() }
    }
}