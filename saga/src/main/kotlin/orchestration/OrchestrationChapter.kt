package orchestration

interface OrchestrationChapter<K> {
    val name: String
    fun process(value: K): ChapterResult<K>
    fun rollback(value: K): ChapterResult<K>
}