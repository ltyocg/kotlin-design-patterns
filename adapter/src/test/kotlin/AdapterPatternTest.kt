import org.mockito.kotlin.spy
import org.mockito.kotlin.verify
import kotlin.test.Test

class AdapterPatternTest {
    @Test
    fun `test adapter`() {
        val adapter: RowingBoat = spy(FishingBoatAdapter())
        Captain(adapter).row()
        verify(adapter).row()
    }
}