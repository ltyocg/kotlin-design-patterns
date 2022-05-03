package choreography

class Saga {
    private val chapters = mutableListOf<Chapter>()
    private var pos = 0
    var isForward = true
        private set
    var finished = false
    val result: SagaResult
        get() = if (finished) {
            if (isForward) SagaResult.FINISHED else SagaResult.ROLLBACKED
        } else SagaResult.PROGRESS

    fun chapter(name: String): Saga {
        chapters.add(Chapter(name))
        return this
    }

    fun setInValue(value: Any?): Saga {
        if (chapters.isNotEmpty()) chapters[chapters.size - 1].inValue = value
        return this
    }

    var currentValue: Any?
        get() = chapters[pos].inValue
        set(value) {
            chapters[pos].inValue = value
        }

    fun setCurrentStatus(result: ChapterResult) {
        chapters[pos].result = result
    }

    fun forward(): Int = ++pos
    fun back(): Int {
        isForward = false
        return --pos
    }

    val current: Chapter
        get() = chapters[pos]
    val isPresent: Boolean
        get() = pos >= 0 && pos < chapters.size
    val isCurrentSuccess: Boolean
        get() = chapters[pos].isSuccess

    class Chapter(val name: String) {
        var result = ChapterResult.INIT
        var inValue: Any? = null
        val isSuccess: Boolean
            get() = result == ChapterResult.SUCCESS
    }

    enum class ChapterResult {
        INIT, SUCCESS, ROLLBACK
    }

    enum class SagaResult {
        PROGRESS, FINISHED, ROLLBACKED
    }

    override fun toString(): String =
        "Saga{chapters=${chapters.toTypedArray().contentToString()}, pos=$pos, forward=$isForward}"
}