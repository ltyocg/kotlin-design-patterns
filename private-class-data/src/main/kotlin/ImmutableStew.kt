import io.github.oshai.kotlinlogging.KotlinLogging

open class ImmutableStew(numPotatoes: Int, numCarrots: Int, numMeat: Int, numPeppers: Int) {
    private val logger = KotlinLogging.logger {}
    protected val data = StewData(numPotatoes, numCarrots, numMeat, numPeppers)
    open fun mix() =
        logger.info { "Mixing the immutable stew we find: ${data.numPotatoes} potatoes, ${data.numCarrots} carrots, ${data.numMeat} meat and ${data.numPeppers} peppers" }
}
