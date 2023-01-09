import org.junit.jupiter.api.Timeout
import java.util.*
import kotlin.test.Test
import kotlin.test.assertContentEquals

class CollectingParameterTest {
    @Test
    @Timeout(1000)
    fun `collecting parameter`() {
        PrinterQueue.emptyQueue()
        val item1 = PrinterItem(PaperSizes.A4, 1, false, true)
        val item2 = PrinterItem(PaperSizes.A4, 10, true, false)
        val item4 = PrinterItem(PaperSizes.A3, 9, false, false)
        val item8 = PrinterItem(PaperSizes.A2, 1, false, false)
        with(PrinterQueue) {
            addPrinterItem(item1)
            addPrinterItem(item2)
            addPrinterItem(PrinterItem(PaperSizes.A4, 4, true, true))
            addPrinterItem(item4)
            addPrinterItem(PrinterItem(PaperSizes.A3, 3, true, true))
            addPrinterItem(PrinterItem(PaperSizes.A3, 3, false, true))
            addPrinterItem(PrinterItem(PaperSizes.A3, 3, true, false))
            addPrinterItem(item8)
            addPrinterItem(PrinterItem(PaperSizes.A2, 2, false, false))
            addPrinterItem(PrinterItem(PaperSizes.A2, 1, true, false))
            addPrinterItem(PrinterItem(PaperSizes.A2, 1, false, true))
        }
        assertContentEquals(
            arrayOf(item1, item2, item4, item8),
            LinkedList<PrinterItem>().apply {
                addValidA4Papers(this)
                addValidA3Papers(this)
                addValidA2Papers(this)
            }.toTypedArray()
        )
    }
}