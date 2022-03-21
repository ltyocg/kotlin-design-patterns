import com.ltyocg.commons.assertLogContains
import org.slf4j.event.Level
import kotlin.test.Test

private const val MESSAGE = "Test Message Printed"
class DelegateTest {
    @Test
    fun `test CanonPrinter`() = assertLogContains(Level.INFO, "Canon Printer : Test Message Printed") {
        PrinterController(CanonPrinter()).print(MESSAGE)
    }

    @Test
    fun `test HpPrinter`() = assertLogContains(Level.INFO, "HP Printer : Test Message Printed") {
        PrinterController(HpPrinter()).print(MESSAGE)
    }

    @Test
    fun `test EpsonPrinter`() = assertLogContains(Level.INFO, "Epson Printer : Test Message Printed") {
        PrinterController(HpPrinter()).print(MESSAGE)
    }
}