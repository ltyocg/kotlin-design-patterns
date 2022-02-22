private const val MESSAGE_TO_PRINT = "hello world"
fun main() {
    PrinterController(HpPrinter()).print(MESSAGE_TO_PRINT)
    PrinterController(CanonPrinter()).print(MESSAGE_TO_PRINT)
    PrinterController(EpsonPrinter()).print(MESSAGE_TO_PRINT)
}