object CompositeEntity {
    fun setData(message: String, signal: String) = ConsoleCoarseGrainedObject.setData(message, signal)
    fun getData(): Array<String?> = ConsoleCoarseGrainedObject.getData()
    fun init() = ConsoleCoarseGrainedObject.init()
}