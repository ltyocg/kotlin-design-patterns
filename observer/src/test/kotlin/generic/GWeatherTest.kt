package generic

import WeatherType
import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.lastMessage
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import kotlin.test.Test
import kotlin.test.assertEquals

class GWeatherTest {
    @Test
    fun `add remove observer`() {
        val observer = mock<Race>()
        val weather = GWeather()
        weather.addObserver(observer)
        verifyNoMoreInteractions(observer)
        val assertListAppender = assertListAppender(GWeather::class)
        weather.timePasses()
        assertEquals("The weather changed to rainy.", assertListAppender.lastMessage())
        verify(observer).update(weather, WeatherType.RAINY)
        weather.removeObserver(observer)
        weather.timePasses()
        assertEquals("The weather changed to windy.", assertListAppender.lastMessage())
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