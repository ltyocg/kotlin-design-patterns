import com.ltyocg.commons.assertLogContains
import kotlin.test.Test

class DelegateTest {
    private val message = "Test Message Printed"

    @Test
    fun `test CanonPrinter`() = assertLogContains("Canon Printer : Test Message Printed") {
        PrinterController(CanonPrinter()).print(message)
    }

    @Test
    fun `test HpPrinter`() = assertLogContains("HP Printer : Test Message Printed") {
        PrinterController(HpPrinter()).print(message)
    }

    @Test
    fun `test EpsonPrinter`() = assertLogContains("Epson Printer : Test Message Printed") {
        PrinterController(HpPrinter()).print(message)
    }
}