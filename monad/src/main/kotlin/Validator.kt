class Validator<T>(private val obj: T) {
    private val exceptions = mutableListOf<Throwable>()
    fun validate(validation: (T) -> Boolean, message: String?): Validator<T> {
        if (!validation(obj)) exceptions.add(IllegalStateException(message))
        return this
    }

    fun <U> validate(projection: (T) -> U, validation: (U) -> Boolean, message: String?): Validator<T> =
        validate({ it.let(projection).let(validation) }, message)

    fun get(): T {
        if (exceptions.isEmpty()) return obj
        throw IllegalStateException().apply { exceptions.forEach { addSuppressed(it) } }
    }
}