import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class HalfSourceTest {
    @Test
    fun accumulateSum() = assertEquals(0, HalfSource.accumulateSum(-1, 0, 1))

    @Test
    fun ifNonZero() = assertFalse(HalfSource.ifNonZero(-1, 0, 1))
}