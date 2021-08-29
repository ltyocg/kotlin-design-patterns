package com.ltyocg.combinator

object Finders {
    fun advancedFinder(query: String, orQuery: String, notQuery: String): Finder =
        Finder.contains(query) or Finder.contains(orQuery) not Finder.contains(notQuery)

    fun filteredFinder(query: String, vararg excludeQueries: String): Finder =
        excludeQueries.fold(Finder.contains(query)) { acc, s -> acc not Finder.contains(s) }

    fun specializedFinder(vararg queries: String): Finder =
        queries.fold(Finder.all) { acc, s -> acc and Finder.contains(s) }

    fun expandedFinder(vararg queries: String): Finder =
        queries.fold(Finder.none) { acc, s -> acc or Finder.contains(s) }
}