import kotlin.test.Test
import kotlin.test.assertTrue

class OrcKingTest {
    private val requests = listOf(
        Request(RequestType.DEFEND_CASTLE, "Don't let the barbarians enter my castle!!"),
        Request(RequestType.TORTURE_PRISONER, "Don't just stand there, tickle him!"),
        Request(RequestType.COLLECT_TAX, "Don't steal, the King hates competition ...")
    )

    @Test
    fun `test makeRequest`() {
        val king = OrcKing()
        requests.forEach {
            king.makeRequest(it)
            assertTrue(it.isHandled, "Expected all requests from King to be handled, but [$it] was not!")
        }
    }
}