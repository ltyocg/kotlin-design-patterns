import io.github.oshai.kotlinlogging.KotlinLogging

sealed interface View {
    fun display()
}

data object ArcherView : View {
    private val logger = KotlinLogging.logger {}
    override fun display() = logger.info { "Displaying archers" }
}

data object CatapultView : View {
    private val logger = KotlinLogging.logger {}
    override fun display() = logger.info { "Displaying catapults" }
}

data object ErrorView : View {
    private val logger = KotlinLogging.logger {}
    override fun display() = logger.error { "Error 500" }
}
