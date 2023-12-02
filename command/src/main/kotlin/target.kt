import io.github.oshai.kotlinlogging.KotlinLogging

abstract class Target {
    private val logger = KotlinLogging.logger {}
    var size: Size = Size.NORMAL
        private set
    var visibility: Visibility = Visibility.VISIBLE
        private set

    fun printStatus() = logger.info { "$this, [size=$size] [visibility=$visibility]" }
    fun changeSize() {
        size = if (size == Size.NORMAL) Size.SMALL else Size.NORMAL
    }

    fun changeVisibility() {
        visibility = if (visibility == Visibility.INVISIBLE) Visibility.VISIBLE else Visibility.INVISIBLE
    }
}

data object Goblin : Target()
