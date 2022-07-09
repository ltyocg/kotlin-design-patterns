import kotlin.test.Test
import kotlin.test.assertEquals

class OldSourceTest {
    @Test
    fun accumulateSum() = assertEquals(0, OldSource.accumulateSum(-1, 0, 1))

    @Test
    fun accumulateMul() = assertEquals(0, OldSource.accumulateMul(-1, 0, 1))
}