import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class NewSourceTest {
    @Test
    fun accumulateSum() = assertEquals(0, NewSource.accumulateSum(-1, 0, 1))

    @Test
    fun accumulateMul() = assertEquals(0, NewSource.accumulateMul(-1, 0, 1))

    @Test
    fun ifNonZero() = assertFalse(NewSource.ifNonZero(-1, 0, 1))
}