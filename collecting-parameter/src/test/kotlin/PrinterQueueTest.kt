import org.junit.jupiter.api.Timeout
import kotlin.test.Test
import kotlin.test.assertFailsWith

class PrinterQueueTest {
    @Test
    @Timeout(1000)
    fun `negative page count`() {
        assertFailsWith<IllegalArgumentException> { PrinterItem(PaperSizes.A4, -1, true, true) }
    }
}