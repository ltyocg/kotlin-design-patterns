import kotlin.test.Test
import kotlin.test.assertEquals

class MainTest {
    @Test
    fun `trampoline with factorial function`() = assertEquals(3_628_800, loop(10, 1).result())
}