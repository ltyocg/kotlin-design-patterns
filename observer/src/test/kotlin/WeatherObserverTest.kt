import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.formattedList
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertContentEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class WeatherObserverTest<O : WeatherObserver>
internal constructor(private val factory: () -> O) {
    abstract fun dataProvider(): Collection<Array<Any>>

    @ParameterizedTest
    @MethodSource("dataProvider")
    fun observer(weather: WeatherType, response: String) {
        val observer = factory()
        val assertListAppender = assertListAppender(observer::class)
        observer.update(weather)
        assertContentEquals(listOf(response), assertListAppender.formattedList())
    }
}