import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.lastMessage
import kotlin.test.Test
import kotlin.test.assertEquals

class DelegateTest {
    private val message = "Test Message Printed"

    @Test
    fun `test CanonPrinter`() {
        val assertListAppender = assertListAppender(CanonPrinter::class)
        PrinterController(CanonPrinter()).print(message)
        assertEquals("Canon Printer : Test Message Printed", assertListAppender.lastMessage())
    }

    @Test
    fun `test HpPrinter`() {
        val assertListAppender = assertListAppender(HpPrinter::class)
        PrinterController(HpPrinter()).print(message)
        assertEquals("HP Printer : Test Message Printed", assertListAppender.lastMessage())
    }

    @Test
    fun `test EpsonPrinter`() {
        val assertListAppender = assertListAppender(EpsonPrinter::class)
        PrinterController(EpsonPrinter()).print(message)
        assertEquals("Epson Printer : Test Message Printed", assertListAppender.lastMessage())
    }
}