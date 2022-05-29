import org.slf4j.LoggerFactory

sealed interface View {
    fun display()
}

object ArcherView : View {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun display() = log.info("Displaying archers")
}

object CatapultView : View {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun display() = log.info("Displaying catapults")
}

object ErrorView : View {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun display() = log.error("Error 500")
}