fun interface Filterer<G, E> {
    fun by(predicate: (E) -> Boolean): G
}