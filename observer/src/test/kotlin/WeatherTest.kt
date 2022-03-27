import com.ltyocg.commons.assertLogContains
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import kotlin.test.Test

class WeatherTest {
    @Test
    fun `add remove observer`() {
        val observer = mock<WeatherObserver>()
        val weather = Weather()
        weather.addObserver(observer)
        verifyNoMoreInteractions(observer)
        assertLogContains("The weather changed to rainy.") {
            weather.timePasses()
        }
        verify(observer).update(WeatherType.RAINY)
        weather.removeObserver(observer)
        assertLogContains("The weather changed to windy.") {
            weather.timePasses()
        }
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun timePasses() {
        val observer = mock<WeatherObserver>()
        val weather = Weather()
        weather.addObserver(observer)
        val inOrder = inOrder(observer)
        val weatherTypes = WeatherType.values()
        (1..19).forEach {
            weather.timePasses()
            inOrder.verify(observer).update(weatherTypes[it % weatherTypes.size])
        }
        verifyNoMoreInteractions(observer)
    }
}