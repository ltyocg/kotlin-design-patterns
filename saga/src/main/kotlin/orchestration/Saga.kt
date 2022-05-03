package orchestration

class Saga {
    private val chapters = mutableListOf<Chapter>()
    fun chapter(name: String): Saga {
        chapters.add(Chapter(name))
        return this
    }

    operator fun get(idx: Int): Chapter = chapters[idx]
    fun isPresent(idx: Int): Boolean = idx in chapters.indices
    enum class Result {
        FINISHED, ROLLBACK, CRASHED
    }

    class Chapter(var name: String)
}