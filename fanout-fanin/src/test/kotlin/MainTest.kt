import kotlin.test.Test
import kotlin.test.assertEquals

class MainTest {
    @Test
    fun `should execute main without exception`() = main()

    @Test
    fun `fanOutFanIn test`() =
        assertEquals(139L, fanOutFanIn(listOf(1L, 3L, 4L, 7L, 8L).map(::SquareNumberRequest), Consumer(0L)))
}