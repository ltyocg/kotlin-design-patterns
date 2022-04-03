import org.mockito.kotlin.spy
import org.mockito.kotlin.verify
import kotlin.test.Test

class AdapterPatternTest {
    @Test
    fun adapter() {
        val adapter = spy(FishingBoatAdapter())
        Captain(adapter).row()
        verify(adapter).row()
    }
}