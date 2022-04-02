class Pipeline<I, O>(private val currentHandler: (I) -> O) {
    fun <K> addHandler(newHandler: (O) -> K): Pipeline<I, K> =
        Pipeline { newHandler(currentHandler(it)) }

    fun execute(input: I): O = currentHandler(input)
}