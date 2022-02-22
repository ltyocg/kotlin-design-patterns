class CompositeEntity {
    private val console = ConsoleCoarseGrainedObject()
    fun setData(message: String, signal: String) = console.setData(message, signal)
    fun getData(): Array<String?> = console.getData()
    fun init() = console.init()
}