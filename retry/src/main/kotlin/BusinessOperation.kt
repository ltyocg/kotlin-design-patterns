fun interface BusinessOperation<T> {
    fun perform(): T
}