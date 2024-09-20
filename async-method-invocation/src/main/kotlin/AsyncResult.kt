interface AsyncResult<T> {
    val isCompleted: Boolean
    val value: T?
    fun await()
}
