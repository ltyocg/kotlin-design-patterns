import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class MainTest {
    @Test
    fun `should execute main without exception`() = runBlocking { main() }
}