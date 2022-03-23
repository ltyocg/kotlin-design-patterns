private const val ERROR = "Error"
private const val MESSAGE = "Message"
fun main() {
    prepare()
    execute()
    unprepare()
}

private fun prepare() {
    FileLoggerModule.prepare()
    ConsoleLoggerModule.prepare()
}

private fun execute() {
    FileLoggerModule.printString(MESSAGE)
    FileLoggerModule.printErrorString(ERROR)
    ConsoleLoggerModule.printString(MESSAGE)
    ConsoleLoggerModule.printErrorString(ERROR)
}

private fun unprepare() {
    FileLoggerModule.unprepare()
    ConsoleLoggerModule.unprepare()
}