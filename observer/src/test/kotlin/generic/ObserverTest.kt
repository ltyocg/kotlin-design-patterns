package generic

import WeatherType
import com.ltyocg.commons.assertLogContentEquals
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class ObserverTest<O : Observer<*, *, WeatherType>>
internal constructor(private val factory: () -> O) {
    abstract fun dataProvider(): Collection<Array<Any>>

    @ParameterizedTest
    @MethodSource("dataProvider")
    fun observer(weather: WeatherType, response: String) {
        val observer = factory()
        assertLogContentEquals(listOf(response)) {
            observer.update(null, weather)
        }
    }
}