package choreography

interface ChoreographyChapter {
    fun execute(saga: Saga): Saga
    val name: String
    fun process(saga: Saga): Saga
    fun rollback(saga: Saga): Saga
}