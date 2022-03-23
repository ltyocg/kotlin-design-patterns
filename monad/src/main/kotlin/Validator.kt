class Validator<T> private constructor(private val obj: T) {
    private val exceptions = mutableListOf<Throwable>()
    fun validate(validation: (T) -> Boolean, message: String?): Validator<T> {
        if (!validation(obj)) exceptions.add(IllegalStateException(message))
        return this
    }

    fun <U> validate(projection: (T) -> U, validation: (U) -> Boolean, message: String?): Validator<T> =
        validate({ validation(projection(obj)) }, message)

    fun get(): T {
        if (exceptions.isEmpty()) return obj
        val e = IllegalStateException()
        exceptions.forEach { e.addSuppressed(it) }
        throw e
    }

    companion object {
        fun <T> of(t: T?): Validator<T> = Validator(t!!)
    }
}