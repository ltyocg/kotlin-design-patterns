package orchestration

class ChapterResult<K> internal constructor(val value: K, private val state: State) {
    val isSuccess: Boolean
        get() = state == State.SUCCESS

    enum class State {
        SUCCESS, FAILURE
    }

    companion object {
        fun <K> success(value: K): ChapterResult<K> = ChapterResult(value, State.SUCCESS)
        fun <K> failure(value: K): ChapterResult<K> = ChapterResult(value, State.FAILURE)
    }
}