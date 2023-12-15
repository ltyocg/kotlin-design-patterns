interface UpdateService<T> {
    fun doUpdate(obj: T, id: Long): T
}
