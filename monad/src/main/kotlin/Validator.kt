class Validator<T>(private val obj: T) {
    private val exceptions = mutableListOf<Throwable>()
    fun <U> validate(projection: (T) -> U, validation: (U) -> Boolean, message: String?): Validator<T> {
        if (!obj.let(projection).let(validation)) exceptions.add(IllegalStateException(message))
        return this
    }

    fun get(): T {
        if (exceptions.isEmpty()) return obj
        throw IllegalStateException().apply { exceptions.forEach { addSuppressed(it) } }
    }
}