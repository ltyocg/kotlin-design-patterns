interface IUnitOfWork<T> {
    fun registerNew(entity: T)
    fun registerModified(entity: T)
    fun registerDeleted(entity: T)
    fun commit()
}