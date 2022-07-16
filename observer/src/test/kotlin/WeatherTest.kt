import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.lastMessage
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import kotlin.test.Test
import kotlin.test.assertEquals

class WeatherTest {
    @Test
    fun `add remove observer`() {
        val observer = mock<WeatherObserver>()
        val weather = Weather()
        weather.addObserver(observer)
        verifyNoMoreInteractions(observer)
        val assertListAppender = assertListAppender(Weather::class)
        weather.timePasses()
        assertEquals("The weather changed to rainy.", assertListAppender.lastMessage())
        verify(observer).update(WeatherType.RAINY)
        weather.removeObserver(observer)
        weather.timePasses()
        assertEquals("The weather changed to windy.", assertListAppender.lastMessage())
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