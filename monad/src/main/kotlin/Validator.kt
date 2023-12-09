class Validator<T>(private val obj: T) {
    private val exceptions = mutableListOf<Throwable>()
    fun validate(validation: Validation<T>, message: String?): Validator<T> = apply {
        if (!validation(obj)) exceptions.add(IllegalStateException(message))
    }

    fun <U> validate(projection: Projection<T, U>, validation: Validation<U>, message: String?): Validator<T> =
        validate({ it.let(projection).let(validation) }, message)

    fun get(): T {
        if (exceptions.isEmpty()) return obj
        throw IllegalStateException().apply { exceptions.forEach { addSuppressed(it) } }
    }

    fun interface Validation<in T> : (T) -> Boolean
    fun interface Projection<in T, out U> : (T) -> U
}
