import java.util.*

object PrinterQueue {
    val printerQueue: Queue<PrinterItem> = LinkedList()
    fun emptyQueue() = printerQueue.clear()
    fun addPrinterItem(printerItem: PrinterItem) {
        printerQueue.add(printerItem)
    }
}