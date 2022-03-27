package generic

import WeatherType
import com.ltyocg.commons.assertLogContains
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import kotlin.test.Test

class GWeatherTest {
    @Test
    fun `add remove observer`() {
        val observer = mock<Race>()
        val weather = GWeather()
        weather.addObserver(observer)
        verifyNoMoreInteractions(observer)
        assertLogContains("The weather changed to rainy.") {
            weather.timePasses()
        }
        verify(observer).update(weather, WeatherType.RAINY)
        weather.removeObserver(observer)
        assertLogContains("The weather changed to windy.") {
            weather.timePasses()
        }
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun timePasses() {
        val observer = mock<Race>()
        val weather = GWeather()
        weather.addObserver(observer)
        val inOrder = inOrder(observer)
        val weatherTypes = WeatherType.values()
        (1..19).forEach {
            weather.timePasses()
            inOrder.verify(observer).update(weather, weatherTypes[it % weatherTypes.size])
        }
        verifyNoMoreInteractions(observer)
    }
}