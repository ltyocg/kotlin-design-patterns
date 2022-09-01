fun interface Trampoline<T> {
    fun get(): T
    fun jump(): Trampoline<T> = this
    fun result(): T = get()
    fun complete(): Boolean = true

    companion object {
        fun <T> done(result: T): Trampoline<T> = Trampoline { result }
        fun <T> more(trampoline: Trampoline<Trampoline<T>>): Trampoline<T> = object : Trampoline<T> {
            override fun complete(): Boolean = false
            override fun jump(): Trampoline<T> = trampoline.result()
            override fun get(): T = generateSequence<Trampoline<T>>(this) { it.jump() }
                .filter { it.complete() }
                .first()
                .result()
        }
    }
}