import java.util.*

fun main() {
    PrinterQueue.addPrinterItem(PrinterItem(PaperSizes.A4, 5, false, false))
    PrinterQueue.addPrinterItem(PrinterItem(PaperSizes.A3, 2, false, false))
    PrinterQueue.addPrinterItem(PrinterItem(PaperSizes.A2, 5, false, false))
    val result = LinkedList<PrinterItem>()
    PrinterQueue.printerQueue.forEach {
        if (when (it.paperSize) {
                PaperSizes.A4 -> it.isColour && !it.isDoubleSided || !it.isColour
                PaperSizes.A3 -> !it.isColour && !it.isDoubleSided
                PaperSizes.A2 -> it.pageCount == 1 && !it.isDoubleSided && !it.isColour
            }
        ) result.add(it)
    }
}

fun addValidA4Papers(printerItemsCollection: Queue<PrinterItem>) =
    printerItemsCollection.addPrinterQueue { it.paperSize == PaperSizes.A4 && (it.isColour && !it.isDoubleSided || !it.isColour) }

fun addValidA3Papers(printerItemsCollection: Queue<PrinterItem>) =
    printerItemsCollection.addPrinterQueue { it.paperSize == PaperSizes.A3 && !it.isColour && !it.isDoubleSided }

fun addValidA2Papers(printerItemsCollection: Queue<PrinterItem>) =
    printerItemsCollection.addPrinterQueue { it.paperSize == PaperSizes.A2 && it.pageCount == 1 && !it.isDoubleSided && !it.isColour }

private fun Queue<PrinterItem>.addPrinterQueue(predicate: (PrinterItem) -> Boolean) =
    PrinterQueue.printerQueue.asSequence().filter(predicate).forEach(::add)