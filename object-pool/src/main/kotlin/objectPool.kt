abstract class ObjectPool<T> {
    private val available: MutableSet<T> = mutableSetOf()
    private val inUse: MutableSet<T> = mutableSetOf()
    protected abstract fun create(): T

    @Synchronized
    fun checkOut(): T {
        if (available.isEmpty()) available.add(create())
        val instance = available.first()
        available.remove(instance)
        inUse.add(instance)
        return instance
    }

    @Synchronized
    fun checkIn(instance: T) {
        inUse.remove(instance)
        available.add(instance)
    }

    @Synchronized
    override fun toString(): String = "Pool available=${available.size} inUse=${inUse.size}"
}

class OliphauntPool : ObjectPool<Oliphaunt>() {
    override fun create(): Oliphaunt = Oliphaunt()
}