import org.slf4j.LoggerFactory

sealed class Tobacco {
    private val log = LoggerFactory.getLogger(javaClass)
    fun smoke(wizard: Wizard) = log.info("{} smoking {}", wizard::class.simpleName, this::class.simpleName)
}

object OldTobyTobacco : Tobacco()
object RivendellTobacco : Tobacco()
object SecondBreakfastTobacco : Tobacco()