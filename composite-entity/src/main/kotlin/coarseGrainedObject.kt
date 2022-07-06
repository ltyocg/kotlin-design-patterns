abstract class CoarseGrainedObject<T> {
    var dependentObjects = arrayOf<DependentObject<T>>()

    @Suppress("UNCHECKED_CAST")
    open fun getData(): Array<T?> = Array<Any?>(dependentObjects.size) { dependentObjects[it].data } as Array<T?>
    open fun setData(vararg data: T) = data.forEachIndexed { index, t -> dependentObjects[index].data = t }
}

object ConsoleCoarseGrainedObject : CoarseGrainedObject<String>() {
    override fun getData(): Array<String?> = arrayOf(dependentObjects[0].data, dependentObjects[1].data)
    fun init() {
        dependentObjects = arrayOf(MessageDependentObject, SignalDependentObject)
    }
}