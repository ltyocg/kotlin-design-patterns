import io.github.oshai.kotlinlogging.KotlinLogging

sealed class Tobacco {
    private val logger = KotlinLogging.logger {}
    fun smoke(wizard: Wizard) = logger.info { "${wizard::class.simpleName} smoking ${this::class.simpleName}" }
}

data object OldTobyTobacco : Tobacco()
data object RivendellTobacco : Tobacco()
data object SecondBreakfastTobacco : Tobacco()
