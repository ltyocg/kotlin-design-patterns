package com.ltyocg.filterer

fun interface Filterer<G, E> {
    fun by(predicate: (E) -> Boolean): G
}