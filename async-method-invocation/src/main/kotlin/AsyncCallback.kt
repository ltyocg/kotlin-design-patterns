interface AsyncCallback<T> {
    fun onComplete(value: T?)
    fun onError(ex: Exception)
}
