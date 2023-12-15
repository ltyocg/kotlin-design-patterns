interface JpaRepository<T> {
    fun findById(id: Long): T
    fun getEntityVersionById(id: Long): Int
    fun update(obj: T): Int
}
