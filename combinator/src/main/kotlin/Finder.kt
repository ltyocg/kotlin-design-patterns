fun interface Finder {
    fun find(text: String): List<String>
    infix fun not(notFinder: Finder): Finder = Finder { find(it) - notFinder.find(it) }
    infix fun or(orFinder: Finder): Finder = Finder { find(it) + orFinder.find(it) }
    infix fun and(andFinder: Finder): Finder = Finder { find(it).flatMap(andFinder::find) }

    companion object {
        val all: Finder = Finder { it.lines() }
        val none: Finder = Finder { emptyList() }
        fun contains(word: String): Finder = Finder { text ->
            text.lineSequence()
                .filter { word.lowercase() in it.lowercase() }
                .toList()
        }
    }
}
